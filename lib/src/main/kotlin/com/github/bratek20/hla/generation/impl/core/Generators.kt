package com.github.bratek20.hla.generation.impl.core

import com.github.bratek20.hla.definitions.api.ModuleDefinition
import com.github.bratek20.hla.directory.api.*
import com.github.bratek20.hla.generation.impl.core.api.ApiTypeFactory
import com.github.bratek20.hla.generation.impl.core.language.LanguageSupport
import com.github.bratek20.hla.velocity.api.VelocityFacade
import com.github.bratek20.hla.velocity.api.VelocityFileContentBuilder

class ModuleGenerationContext(
    val domain: DomainContext,
    val velocity: VelocityFacade,
    val language: LanguageSupport,
    val onlyUpdate: Boolean,
    val onlyPatterns: List<String>
) {
    val module: ModuleDefinition
        get() = domain.module

    val apiTypeFactory: ApiTypeFactory
        get() = ApiTypeFactory(domain.queries, language.types())
}

interface ContentBuilderExtension{
    fun extend(builder: VelocityFileContentBuilder)
}

enum class GeneratorMode {
    START_AND_UPDATE,
    ONLY_START,
}

abstract class ModulePartGenerator {
    lateinit var c: ModuleGenerationContext
    lateinit var apiTypeFactory: ApiTypeFactory
    lateinit var velocityPath: String

    open fun velocityPathOverride(): String? {
        return null
    }

    open fun init(c: ModuleGenerationContext, velocityPath: String) {
        this.c = c
        this.apiTypeFactory = ApiTypeFactory(c.domain.queries, c.language.types())
        this.velocityPath = velocityPath
    }

    protected val module
        get() = c.module

    protected val modules
        get() = c.domain.queries

    protected val language
        get() = c.language

    protected fun contentBuilder(fileName: String): VelocityFileContentBuilder {
        val velocityPath = velocityPathOverride() ?: this.velocityPath
        val path = "templates/${c.language.name().name.lowercase()}/${velocityPath}/$fileName"

        val builder = c.velocity.contentBuilder(path)
            .put("moduleName", module.getName().value)

        c.language.contentBuilderExtensions().forEach { it.extend(builder) }

        return builder
    }

    open fun mode(): GeneratorMode {
        return GeneratorMode.START_AND_UPDATE
    }

    fun shouldSkip(): Boolean {
        if(c.onlyUpdate && mode() == GeneratorMode.ONLY_START) {
            return true
        }

        if(c.onlyPatterns.isNotEmpty()) {
            if (c.onlyPatterns.contains(name())) {
                return false
            }
            return children().all { it.shouldSkip() }
        }
        return false
    }

    abstract fun name(): String

    open fun children(): List<ModulePartGenerator> {
        return emptyList()
    }
}

abstract class FileGenerator
    : ModulePartGenerator()
{
    abstract fun generateFileContent(): FileContent?

    fun generateFile(): File? {
        var content = generateFileContent() ?: return null
        if (mode() == GeneratorMode.START_AND_UPDATE) {
            val lines = listOf(
                "// DO NOT EDIT! Autogenerated by HLA tool",
                ""
            ) + content.lines
            content = FileContent(lines)
        }
        return File(
            name = name() + "." + language.filesExtension(),
            content = content.toString()
        )
    }
}

abstract class DirectoryGenerator
    : ModulePartGenerator()
{
    private lateinit var fileGenerators: List<FileGenerator>
    private lateinit var directoryGenerators: List<DirectoryGenerator>

    //TODO-REF current abstraction seems off
    // directory should not have mode, it simply is generated if has some file or directory
    // so maybe it belongs only in FileGenerator
    // but then ModulePartGenerator logic should be in DirectoryGenerator I guess
    final override fun mode(): GeneratorMode {
        return GeneratorMode.START_AND_UPDATE
    }

    override fun init(c: ModuleGenerationContext, velocityPath: String) {
        super.init(c, velocityPath)

        fileGenerators = getFileGenerators()
        directoryGenerators = getDirectoryGenerators()

        children().forEach { it.init(c, velocityDirPath()) }
    }

    open fun velocityDirPath(): String {
        return ""
    }

    open fun shouldGenerateDirectory(): Boolean {
        return true
    }

    open fun getFileGenerators(): List<FileGenerator> {
        return emptyList()
    }

    open fun getDirectoryGenerators(): List<DirectoryGenerator> {
        return emptyList()
    }

    override fun children(): List<ModulePartGenerator> {
        return fileGenerators + directoryGenerators
    }

    fun generateDirectory(): Directory? {
        if (!shouldGenerateDirectory()) {
            return null
        }

        val files = mutableListOf<File>()
        fileGenerators.forEach { fileGenerator ->
            if (fileGenerator.shouldSkip()) {
                return@forEach
            }
            fileGenerator.generateFile()?.let { files.add(it) }
        }

        val directories = mutableListOf<Directory>()
        directoryGenerators.forEach { dirGenerator ->
            if (dirGenerator.shouldSkip()) {
                return@forEach
            }
            dirGenerator.generateDirectory()?.let { directories.add(it) }
        }

        if (files.isEmpty() && directories.isEmpty()) {
            return null
        }

        return Directory(
            name = language.adjustDirectoryName(name()),
            files = files,
            directories = directories
        )
    }
}