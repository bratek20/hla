package pl.bratek20.hla

import pl.bratek20.hla.directory.api.Path
import pl.bratek20.hla.directory.impl.DirectoryLogic
import pl.bratek20.hla.generation.api.GeneratorLanguage
import pl.bratek20.hla.generation.impl.CodeGeneratorImpl
import pl.bratek20.hla.model.*

fun exampleModule(): HlaModule {
    return HlaModule(
        name = "example",
        simpleValueObjects = listOf(
            SimpleValueObject(
                name = "SomeClass",
                type = "SomeClass"
            ),
            SimpleValueObject(
                name = "SomeClass2",
                type = "SomeClass2"
            )
        ),
        complexValueObjects = listOf(
            ComplexValueObject(
                name = "SomeClass",
                fields = listOf(
                    Field(
                        name = "id",
                        type = "String"
                    ),
                    Field(
                        name = "amount",
                        type = "Int"
                    )
                )
            ),
            ComplexValueObject(
                name = "SomeClass2",
                fields = listOf(
                    Field(
                        name = "id",
                        type = "String"
                    ),
                    Field(
                        name = "enabled",
                        type = "Boolean"
                    )
                )
            )
        ),
        interfaces = emptyList()
    )
}

fun main() {
    val module = exampleModule()

    val dir = CodeGeneratorImpl().generateCode(module, GeneratorLanguage.KOTLIN)

    DirectoryLogic().writeDirectory(Path("tmp"), dir)
}