// DO NOT EDIT! Autogenerated by HLA tool

package pl.bratek20.hla.definitions.fixtures

import pl.bratek20.hla.facade.api.*
import pl.bratek20.hla.facade.fixtures.*

import pl.bratek20.hla.definitions.api.*

data class KeyDefinitionDef(
    var name: String = "someValue",
    var type: (TypeDefinitionDef.() -> Unit) = {},
)
fun keyDefinition(init: KeyDefinitionDef.() -> Unit = {}): KeyDefinition {
    val def = KeyDefinitionDef().apply(init)
    return KeyDefinition(
        name = def.name,
        type = typeDefinition(def.type),
    )
}

data class EnumDefinitionDef(
    var name: String = "someValue",
    var values: List<String> = emptyList(),
)
fun enumDefinition(init: EnumDefinitionDef.() -> Unit = {}): EnumDefinition {
    val def = EnumDefinitionDef().apply(init)
    return EnumDefinition(
        name = def.name,
        values = def.values,
    )
}

data class ImplSubmoduleDefinitionDef(
    var data: List<(ComplexStructureDefinitionDef.() -> Unit)> = emptyList(),
    var dataKeys: List<(KeyDefinitionDef.() -> Unit)> = emptyList(),
)
fun implSubmoduleDefinition(init: ImplSubmoduleDefinitionDef.() -> Unit = {}): ImplSubmoduleDefinition {
    val def = ImplSubmoduleDefinitionDef().apply(init)
    return ImplSubmoduleDefinition(
        data = def.data.map { it -> complexStructureDefinition(it) },
        dataKeys = def.dataKeys.map { it -> keyDefinition(it) },
    )
}

data class ModuleDefinitionDef(
    var name: String = "someValue",
    var simpleCustomTypes: List<(SimpleStructureDefinitionDef.() -> Unit)> = emptyList(),
    var complexCustomTypes: List<(ComplexStructureDefinitionDef.() -> Unit)> = emptyList(),
    var simpleValueObjects: List<(SimpleStructureDefinitionDef.() -> Unit)> = emptyList(),
    var complexValueObjects: List<(ComplexStructureDefinitionDef.() -> Unit)> = emptyList(),
    var dataClasses: List<(ComplexStructureDefinitionDef.() -> Unit)> = emptyList(),
    var interfaces: List<(InterfaceDefinitionDef.() -> Unit)> = emptyList(),
    var propertyKeys: List<(KeyDefinitionDef.() -> Unit)> = emptyList(),
    var dataKeys: List<(KeyDefinitionDef.() -> Unit)> = emptyList(),
    var enums: List<(EnumDefinitionDef.() -> Unit)> = emptyList(),
    var implSubmodule: (ImplSubmoduleDefinitionDef.() -> Unit) = {},
)
fun moduleDefinition(init: ModuleDefinitionDef.() -> Unit = {}): ModuleDefinition {
    val def = ModuleDefinitionDef().apply(init)
    return ModuleDefinition(
        name = ModuleName(def.name),
        simpleCustomTypes = def.simpleCustomTypes.map { it -> simpleStructureDefinition(it) },
        complexCustomTypes = def.complexCustomTypes.map { it -> complexStructureDefinition(it) },
        simpleValueObjects = def.simpleValueObjects.map { it -> simpleStructureDefinition(it) },
        complexValueObjects = def.complexValueObjects.map { it -> complexStructureDefinition(it) },
        dataClasses = def.dataClasses.map { it -> complexStructureDefinition(it) },
        interfaces = def.interfaces.map { it -> interfaceDefinition(it) },
        propertyKeys = def.propertyKeys.map { it -> keyDefinition(it) },
        dataKeys = def.dataKeys.map { it -> keyDefinition(it) },
        enums = def.enums.map { it -> enumDefinition(it) },
        implSubmodule = implSubmoduleDefinition(def.implSubmodule),
    )
}

data class TypeDefinitionDef(
    var name: String = "someValue",
    var wrappers: List<TypeWrapper> = emptyList(),
)
fun typeDefinition(init: TypeDefinitionDef.() -> Unit = {}): TypeDefinition {
    val def = TypeDefinitionDef().apply(init)
    return TypeDefinition(
        name = def.name,
        wrappers = def.wrappers.map { it -> it },
    )
}

data class FieldDefinitionDef(
    var name: String = "someValue",
    var type: (TypeDefinitionDef.() -> Unit) = {},
    var attributes: List<(AttributeDef.() -> Unit)> = emptyList(),
    var defaultValue: String? = null,
)
fun fieldDefinition(init: FieldDefinitionDef.() -> Unit = {}): FieldDefinition {
    val def = FieldDefinitionDef().apply(init)
    return FieldDefinition(
        name = def.name,
        type = typeDefinition(def.type),
        attributes = def.attributes.map { it -> attribute(it) },
        defaultValue = def.defaultValue,
    )
}

data class AttributeDef(
    var name: String = "someValue",
    var value: String = "someValue",
)
fun attribute(init: AttributeDef.() -> Unit = {}): Attribute {
    val def = AttributeDef().apply(init)
    return Attribute(
        name = def.name,
        value = def.value,
    )
}

data class SimpleStructureDefinitionDef(
    var name: String = "someValue",
    var typeName: String = "someValue",
    var attributes: List<(AttributeDef.() -> Unit)> = emptyList(),
)
fun simpleStructureDefinition(init: SimpleStructureDefinitionDef.() -> Unit = {}): SimpleStructureDefinition {
    val def = SimpleStructureDefinitionDef().apply(init)
    return SimpleStructureDefinition(
        name = def.name,
        typeName = def.typeName,
        attributes = def.attributes.map { it -> attribute(it) },
    )
}

data class ComplexStructureDefinitionDef(
    var name: String = "someValue",
    var fields: List<(FieldDefinitionDef.() -> Unit)> = emptyList(),
)
fun complexStructureDefinition(init: ComplexStructureDefinitionDef.() -> Unit = {}): ComplexStructureDefinition {
    val def = ComplexStructureDefinitionDef().apply(init)
    return ComplexStructureDefinition(
        name = def.name,
        fields = def.fields.map { it -> fieldDefinition(it) },
    )
}

data class InterfaceDefinitionDef(
    var name: String = "someValue",
    var methods: List<(MethodDefinitionDef.() -> Unit)> = emptyList(),
)
fun interfaceDefinition(init: InterfaceDefinitionDef.() -> Unit = {}): InterfaceDefinition {
    val def = InterfaceDefinitionDef().apply(init)
    return InterfaceDefinition(
        name = def.name,
        methods = def.methods.map { it -> methodDefinition(it) },
    )
}

data class ArgumentDefinitionDef(
    var name: String = "someValue",
    var type: (TypeDefinitionDef.() -> Unit) = {},
)
fun argumentDefinition(init: ArgumentDefinitionDef.() -> Unit = {}): ArgumentDefinition {
    val def = ArgumentDefinitionDef().apply(init)
    return ArgumentDefinition(
        name = def.name,
        type = typeDefinition(def.type),
    )
}

data class ExceptionDefinitionDef(
    var name: String = "someValue",
)
fun exceptionDefinition(init: ExceptionDefinitionDef.() -> Unit = {}): ExceptionDefinition {
    val def = ExceptionDefinitionDef().apply(init)
    return ExceptionDefinition(
        name = def.name,
    )
}

data class MethodDefinitionDef(
    var name: String = "someValue",
    var returnType: (TypeDefinitionDef.() -> Unit) = {},
    var args: List<(ArgumentDefinitionDef.() -> Unit)> = emptyList(),
    var throws: List<(ExceptionDefinitionDef.() -> Unit)> = emptyList(),
)
fun methodDefinition(init: MethodDefinitionDef.() -> Unit = {}): MethodDefinition {
    val def = MethodDefinitionDef().apply(init)
    return MethodDefinition(
        name = def.name,
        returnType = typeDefinition(def.returnType),
        args = def.args.map { it -> argumentDefinition(it) },
        throws = def.throws.map { it -> exceptionDefinition(it) },
    )
}