package pl.bratek20.hla.generation.impl

import pl.bratek20.hla.directory.api.Directory
import pl.bratek20.hla.directory.api.File
import pl.bratek20.hla.generation.api.CodeGenerator
import pl.bratek20.hla.model.ComplexValueObject
import pl.bratek20.hla.model.HlaModule
import pl.bratek20.hla.model.Interface
import pl.bratek20.hla.model.SimpleValueObject
import pl.bratek20.hla.velocity.api.VelocityFileContentBuilder
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
        val interfaceFiles = module.interfaces.map {
            interfaceFile(module.name, it)
        }

        return Directory(
            name = module.name.lowercase(),
            directories = listOf(Directory(
                name = "api",
                files = simpleValueObjectFiles + complexValueObjectFiles + interfaceFiles,
            ))
        )
    }

    private fun simpleValueObjectFile(moduleName: String, vo: SimpleValueObject): File {
        val fileContent = contentBuilder("templates/simpleValueObject.vm", moduleName)
            .put("packageName", "pl.bratek20.${moduleName.lowercase()}")
            .put("vo", vo)
            .build()

        return File(
            name = vo.name + ".kt",
            content = fileContent
        )
    }

    private fun complexValueObjectFile(moduleName: String, vo: ComplexValueObject): File {
        val fileContent = contentBuilder("templates/complexValueObject.vm", moduleName)
            .put("vo", vo)
            .build()

        return File(
            name = vo.name + ".kt",
            content = fileContent
        )
    }

    private fun interfaceFile(moduleName: String, interf: Interface): File {
        val fileContent = contentBuilder("templates/interface.vm", moduleName)
            .put("interface", interf)
            .build()

        return File(
            name = interf.name + ".kt",
            content = fileContent
        )
    }

    private fun contentBuilder(templatePath: String, moduleName: String): VelocityFileContentBuilder {
        return velocity.contentBuilder(templatePath)
            .put("packageName", "pl.bratek20.api.${moduleName.lowercase()}")
    }
}