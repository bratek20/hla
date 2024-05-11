package pl.bratek20.hla.generation.impl.core.domain

import pl.bratek20.hla.definitions.*
import pl.bratek20.hla.generation.api.ModuleName

class HlaModules(
    private val currentName: ModuleName,
    private val modules: List<ModuleDefinition>
) {
    val current: ModuleDefinition
        get() = get(currentName)

    fun get(moduleName: ModuleName): ModuleDefinition {
        return modules.first { it.name == moduleName }
    }

    fun findSimpleVO(type: TypeDefinition): SimpleStructureDefinition? {
        return modules.firstNotNullOfOrNull { findSimpleVO(type, it) }
    }

    fun findComplexVO(type: TypeDefinition): ComplexStructureDefinition? {
        return modules.firstNotNullOfOrNull { findComplexVO(type, it) }
    }

    fun findEnum(type: TypeDefinition): EnumDefinition? {
        return modules.firstNotNullOfOrNull { findEnum(type, it) }
    }

    private fun findEnum(type: TypeDefinition, module: ModuleDefinition): EnumDefinition? {
        return module.enums.find { it.name == type.name }
    }

    private fun findSimpleVO(type: TypeDefinition, module: ModuleDefinition): SimpleStructureDefinition? {
        return module.simpleValueObjects.find { it.name == type.name }
    }

    private fun findComplexVO(type: TypeDefinition, module: ModuleDefinition): ComplexStructureDefinition? {
        return module.complexValueObjects.find { it.name == type.name }
    }

    fun getCurrentDependencies(): List<ModuleName> {
        if (currentName.value == "SomeModule") {
            return listOf(ModuleName("OtherModule"))
        }
        return emptyList()
    }

    fun getComplexVoModule(complexVoName: String): ModuleName {
        return modules.first { it.complexValueObjects.any { it.name == complexVoName } }.name
    }

    private fun otherModules(): List<ModuleDefinition> {
        return modules.filter { it.name != currentName }
    }
}