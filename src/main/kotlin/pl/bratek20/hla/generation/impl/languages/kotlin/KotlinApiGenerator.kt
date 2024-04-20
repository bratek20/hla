package pl.bratek20.hla.generation.impl.languages.kotlin

import pl.bratek20.hla.generation.impl.core.domain.ModuleGenerationContext
import pl.bratek20.hla.generation.impl.core.api.ApiGenerator

class KotlinApiGenerator(c: ModuleGenerationContext)
    : ApiGenerator(c, KotlinTypes()) {

    override fun dirName(): String {
        return "api"
    }

    override fun valueObjectsFileName(): String {
        return "ValueObjects.kt"
    }

    override fun interfacesFileName(): String {
        return "Interfaces.kt"
    }
}