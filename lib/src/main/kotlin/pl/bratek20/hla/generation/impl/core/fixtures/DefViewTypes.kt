package pl.bratek20.hla.generation.impl.core.fixtures

import pl.bratek20.hla.generation.impl.core.api.*
import pl.bratek20.hla.generation.impl.core.language.LanguageBuildersPattern
import pl.bratek20.hla.generation.impl.core.language.LanguageTypes
import pl.bratek20.hla.utils.pascalToCamelCase

abstract class DefType<T: ApiType>(
    val api: T
) {
    protected val languageTypes: LanguageTypes
        get() = api.languageTypes

    lateinit var pattern: LanguageBuildersPattern

    abstract fun name(): String

    abstract fun defaultValue(): String

    abstract fun build(variableName: String): String
}

class BaseDefType(
    api: BaseApiType,
) : DefType<BaseApiType>(api) {
    override fun name(): String {
        return api.name()
    }

    override fun defaultValue(): String {
        return api.languageTypes.defaultValueForBaseType(api.name)
    }

    override fun build(variableName: String): String {
        return variableName
    }
}

abstract class SimpleStructureDefType<T: SimpleStructureApiType>(
    api: T,
    private val boxedType: BaseDefType
) : DefType<T>(api) {
    override fun build(variableName: String): String {
        return api.deserialize(variableName)
    }

    override fun name(): String {
        return boxedType.name()
    }

    override fun defaultValue(): String {
        return api.exampleValue() ?: boxedType.defaultValue()
    }
}

class SimpleVODefType(
    api: SimpleValueObjectApiType,
    boxedType: BaseDefType
) : SimpleStructureDefType<SimpleValueObjectApiType>(api, boxedType) {
}

class SimpleCustomDefType(
    api: SimpleCustomApiType,
    boxedType: BaseDefType
) : SimpleStructureDefType<SimpleCustomApiType>(api, boxedType) {
}

open class DefField(
    val api: ApiTypeField,
    private val factory: DefTypeFactory
) {
    val name = api.name

    val type by lazy {
        factory.create(api.type)
    }

    open fun build(variableName: String): String {
        return type.build("${variableName}.${name}")
    }

    open fun build(): String {
        return type.build(name)
    }

    // used by velocity
    fun defaultValue(): String {
        if (api.type is OptionalApiType) {
            return type.defaultValue()
        }
        return api.exampleValue() ?:
            api.defaultValue() ?:
            type.defaultValue()
    }
}

open class ComplexStructureDefType(
    api: ComplexStructureApiType<*>,
    val fields: List<DefField>
) : DefType<ComplexStructureApiType<*>>(api) {
    fun funName(): String {
        return pascalToCamelCase(api.name())
    }

    fun defName(): String {
        return api.name() + "Def"
    }

    override fun name(): String {
        return pattern.defClassType(api.name());
    }

    override fun defaultValue(): String {
        return "{}"
    }

    override fun build(variableName: String): String {
        return pattern.complexVoDefConstructor(api.name(), variableName)
    }
}

class ComplexVODefType(
    api: ComplexStructureApiType<*>,
    fields: List<DefField>
) : ComplexStructureDefType(api, fields)

class ComplexCustomDefType(
    api: ComplexStructureApiType<*>,
    fields: List<DefField>
) : ComplexStructureDefType(api, fields)

class OptionalDefType(
    api: OptionalApiType,
    val wrappedType: DefType<*>
) : DefType<OptionalApiType>(api) {
    override fun name(): String {
        if (wrappedType is BaseDefType) {
            return pattern.defOptionalBaseType(wrappedType.name())
        }
        if (wrappedType is SimpleStructureDefType<*>) {
            return pattern.defOptionalBaseType(wrappedType.api.serializableName())
        }
        return pattern.defOptionalType(wrappedType.api.name())
    }

    override fun defaultValue(): String {
        return languageTypes.defaultValueForDefOptional()
    }

    override fun build(variableName: String): String {
        if (wrappedType is BaseDefType) {
            return pattern.mapOptionalDefBaseElement(variableName)
        }
        return pattern.mapOptionalDefElement(variableName, "it", wrappedType.build("it"))
    }
}

class ListDefType(
    api: ListApiType,
    val wrappedType: DefType<*>
) : DefType<ListApiType>(api) {
    override fun name(): String {
        return languageTypes.wrapWithList(wrappedType.name())
    }

    override fun defaultValue(): String {
        return languageTypes.defaultValueForList()
    }

    override fun build(variableName: String): String {
        if (wrappedType is BaseDefType) {
            return variableName
        }
        return languageTypes.mapListElements(variableName, "it", wrappedType.build("it"))
    }
}

class EnumDefType(
    api: EnumApiType
) : DefType<EnumApiType>(api) {
    override fun name(): String {
        return api.name()
    }

    override fun defaultValue(): String {
        return api.defaultValue()
    }

    override fun build(variableName: String): String {
        return variableName
    }
}

class DefTypeFactory(
    private val pattern: LanguageBuildersPattern
) {
    fun create(type: ApiType): DefType<*> {
        val result = when (type) {
            is BaseApiType -> BaseDefType(type)
            is SimpleValueObjectApiType -> SimpleVODefType(type, create(type.boxedType) as BaseDefType)
            is ToRemoveComplexVOApiType -> ComplexVODefType(type, createFields(type.fields))
            is OptionalApiType -> OptionalDefType(type, create(type.wrappedType))
            is ListApiType -> ListDefType(type, create(type.wrappedType))
            is EnumApiType -> EnumDefType(type)
            is SimpleCustomApiType -> SimpleCustomDefType(type, create(type.boxedType) as BaseDefType)
            is ComplexCustomApiType -> ComplexCustomDefType(type, createFields(type.fields))
            is SerializableApiType -> ComplexStructureDefType(type, createFields(type.fields))
            else -> throw IllegalArgumentException("Unknown type: $type")
        }

        result.pattern = pattern

        return result
    }

    private fun createFields(fields: List<ApiTypeField>): List<DefField> {
        return fields.map { DefField(it, this) }
    }
}
