package pl.bratek20.hla.generation.impl.core.fixtures

import pl.bratek20.hla.directory.api.FileContent
import pl.bratek20.hla.generation.impl.core.FileGenerator

class BuildersGenerator: FileGenerator() {
    override fun name(): String {
        return "Builders"
    }

    override fun generateFileContent(): FileContent? {
        val defTypes = module.complexCustomTypes + module.properties + module.valueObjects
        if (defTypes.isEmpty()) {
            return null
        }

        val defTypeFactory = DefTypeFactory(c.language.buildersFixture())

        val builders = (defTypes).map {
            defTypeFactory.create(apiTypeFactory.create(it))
        }

        return contentBuilder("builders.vm")
            .put("builders", builders)
            .build()
    }
}