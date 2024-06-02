package pl.bratek20.hla.generation.impl.core.tests

import pl.bratek20.hla.directory.api.FileContent
import pl.bratek20.hla.generation.impl.core.DirectoryGenerator
import pl.bratek20.hla.generation.impl.core.FileGenerator
import pl.bratek20.hla.generation.impl.core.GeneratorMode

class ApiTestGenerator: FileGenerator() {
    override fun name(): String {
        return "ApiTest"
    }

    override fun mode(): GeneratorMode {
        return GeneratorMode.ONLY_START
    }

    override fun generateFileContent(): FileContent? {
        return contentBuilder("apiTest.vm")
            .build()
    }
}

class TestsGenerator: DirectoryGenerator() {
    override fun name(): String {
        return "Tests"
    }

    override fun mode(): GeneratorMode {
        return GeneratorMode.ONLY_START
    }

    override fun velocityDirPath(): String {
        return "tests"
    }

    override fun getFileGenerators(): List<FileGenerator> {
        return listOf(
            ApiTestGenerator(),
        )
    }
}