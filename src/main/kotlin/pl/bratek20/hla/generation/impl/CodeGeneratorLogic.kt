package pl.bratek20.hla.generation.impl

import pl.bratek20.hla.directory.api.Directory
import pl.bratek20.hla.directory.api.File
import pl.bratek20.hla.directory.api.FileContentBuilder
import pl.bratek20.hla.generation.api.CodeGenerator
import pl.bratek20.hla.model.ComplexValueObject
import pl.bratek20.hla.model.HlaModule
import pl.bratek20.hla.model.SimpleValueObject
import pl.bratek20.hla.velocity.impl.VelocityFacadeImpl

class CodeGeneratorLogic : CodeGenerator {
    private val velocity = VelocityFacadeImpl() // TODO proper injection

    override fun generateCode(module: HlaModule): Directory {
        val simpleValueObjectFiles = module.simpleValueObjects.map {
            simpleValueObjectFile(module.name, it)
        }
        val complexValueObjectFiles = module.complexValueObjects.map {
            complexValueObjectFile(module.name, it)
        }

        return Directory(
            name = module.name.lowercase(),
            files = simpleValueObjectFiles + complexValueObjectFiles,
            directories = emptyList()
        )
    }

    private fun simpleValueObjectFile(moduleName: String, vo: SimpleValueObject): File {
        val fileContent = velocity.contentBuilder("templates/simpleValueObject.vm")
            .put("packageName", "pl.bratek20.${moduleName.lowercase()}")
            .put("className", vo.name)
            .put("type", vo.type)
            .build()

        return File(
            name = vo.name + ".kt",
            content = fileContent
        )
    }

    private fun complexValueObjectFile(moduleName: String, vo: ComplexValueObject): File {
        val builder = FileContentBuilder()
            .addLine("package pl.bratek20.${moduleName.lowercase()}")
            .addLine("")
            .addLine("data class ${vo.name}(")

        vo.fields.forEachIndexed { index, field ->
            builder.addLine("    val ${field.name}: ${field.type}" + if (index == vo.fields.size - 1) "" else ",")
        }

        return File(
            name = vo.name + ".kt",
            content = builder
                .addLine(")")
                .build()
        )
    }
}