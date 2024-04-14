package pl.bratek20.hla.generation.impl.core

import pl.bratek20.hla.model.BuiltInType
import pl.bratek20.hla.model.Type
import pl.bratek20.hla.model.TypeWrapper

abstract class Types {

    private fun isBuiltInType(type: String): Boolean {
        return BuiltInType.entries.find { it.name == type.uppercase() } != null
    }

    private fun toBuiltInType(type: String): BuiltInType {
        return BuiltInType.valueOf(type.uppercase())
    }

    protected abstract fun mapBuiltInType(type: BuiltInType?): String
    protected abstract fun defaultValueForBuiltInType(type: BuiltInType): String

    fun map(type: Type?): String {
        if (type == null) {
            return mapBuiltInType(null)
        }
        if (isList(type)) {
            return "List<${map(type.copy(wrappers = type.wrappers - TypeWrapper.LIST))}>"
        }
        if (isBuiltInType(type.name)) {
            return mapBuiltInType(toBuiltInType(type.name))
        }
        return type.name
    }

    private fun isList(type: Type): Boolean {
        return type.wrappers.contains(TypeWrapper.LIST)
    }

    fun defaultValue(type: Type): String {
        if (isList(type)) {
            return "emptyList()"
        }
        if (isBuiltInType(type.name)) {
            return defaultValueForBuiltInType(toBuiltInType(type.name))
        }
        return "null"
    }
}