package pl.bratek20.hla.parsing

import org.junit.jupiter.api.Test
import pl.bratek20.architecture.context.someContextBuilder
import pl.bratek20.hla.directory.api.Path
import pl.bratek20.hla.definitions.api.ModuleDefinition
import pl.bratek20.hla.definitions.api.TypeWrapper
import pl.bratek20.hla.definitions.assertModules
import pl.bratek20.hla.parsing.api.ModuleDefinitionsParser
import pl.bratek20.hla.parsing.impl.ParsingContextModule

class ModuleDefinitionsParserTest {
    private val parser = someContextBuilder()
        .withModule(ParsingContextModule())
        .build()
        .get(ModuleDefinitionsParser::class.java)

    private fun parse(pathSuffix: String): List<ModuleDefinition> {
        val fullPath = "src/test/resources/parsing/$pathSuffix"
        return parser.parse(Path(fullPath))
    }

    @Test
    fun `should parse two modules`() {
        val modules = parse("two-modules")

        assertModules(modules, listOf(
            {
                name = "OtherModule"
                simpleValueObjects = listOf {
                    name = "OtherId"
                    type = "string"
                }
                complexValueObjects = listOf {
                    name = "OtherClass"
                    fields = listOf(
                        {
                            name = "id"
                            type = {
                                name = "OtherId"
                            }
                        },
                        {
                            name = "amount"
                            type = {
                                name = "int"
                            }
                        }
                    )
                }
            },
            {
                name = "SomeModule"
                simpleValueObjects = listOf {
                    name = "SomeId"
                    type = "string"
                }
                complexValueObjects = listOf(
                    {
                        name = "SomeClass"
                        fields = listOf(
                            {
                                name = "id"
                                type = {
                                    name = "SomeId"
                                }
                            },
                            {
                                name = "amount"
                                type = {
                                    name = "int"
                                }
                            }
                        )
                    },
                    {
                        name = "SomeClass2"
                        fields = listOf(
                            {
                                name = "id"
                                type = {
                                    name = "SomeId"
                                }
                            },
                            {
                                name = "enabled"
                                type = {
                                    name = "bool"
                                }
                            },
                            {
                                name = "names"
                                type = {
                                    name = "string"
                                    wrappers = listOf(
                                        TypeWrapper.LIST
                                    )
                                }
                            },
                            {
                                name = "ids"
                                type = {
                                    name = "SomeId"
                                    wrappers = listOf(
                                        TypeWrapper.LIST
                                    )
                                }
                            }
                        )
                    },
                    {
                        name = "SomeClass3"
                        fields = listOf(
                            {
                                name = "class2Object"
                                type = {
                                    name = "SomeClass2"
                                }
                            },
                            {
                                name = "class2List"
                                type = {
                                    name = "SomeClass2"
                                    wrappers = listOf(
                                        TypeWrapper.LIST
                                    )
                                }
                            }
                        )
                    },
                    {
                        name = "SomeClass4"
                        fields = listOf(
                            {
                                name = "otherId"
                                type = {
                                    name = "OtherId"
                                }
                            },
                            {
                                name = "otherClass"
                                type = {
                                    name = "OtherClass"
                                }
                            },
                            {
                                name = "otherIdList"
                                type = {
                                    name = "OtherId"
                                    wrappers = listOf(
                                        TypeWrapper.LIST
                                    )
                                }
                            },
                            {
                                name = "otherClassList"
                                type = {
                                    name = "OtherClass"
                                    wrappers = listOf(
                                        TypeWrapper.LIST
                                    )
                                }
                            }
                        )
                    }
                )
                interfaces = listOf {
                    name = "SomeInterface"
                    methods = listOf (
                        {
                            name = "someCommand"
                            returnType = {
                                name = "void"
                            }
                            args = listOf (
                                {
                                    name = "id"
                                    type = {
                                        name = "SomeId"
                                    }
                                },
                                {
                                    name = "amount"
                                    type = {
                                        name = "int"
                                    }
                                }
                            )
                            throws = listOf (
                                {
                                    name = "SomeException"
                                },
                                {
                                    name = "SomeException2"
                                }
                            )
                        },
                        {
                            name = "someQuery"
                            returnType = {
                                name = "SomeClass"
                            }
                            args = listOf {
                                name = "id"
                                type = {
                                    name = "SomeId"
                                }
                            }
                        }
                    )
                }
                enums = listOf {
                    name = "SomeEnum"
                    values = listOf(
                        "VALUE_A",
                        "VALUE_B"
                    )
                }
            }
        ))
    }

    @Test
    fun `should parse properties`() {
        val modules = parse("only-properties")

        assertModules(modules, listOf {
            propertyValueObjects = listOf(
                {
                    name = "SomeElement"
                    fields = listOf {
                        name = "id"
                        type = {
                            name = "SomeId"
                        }
                    }
                },
                {
                    name = "SomeConfig"
                    fields = listOf {
                        name = "enabled"
                        type = {
                            name = "bool"
                        }
                    }
                }
            )
            propertyMappings = listOf(
                {
                    key = "someElements"
                    type = {
                        name = "SomeElement"
                        wrappers = listOf(
                            TypeWrapper.LIST
                        )
                    }
                },
                {
                    key = "someConfig"
                    type = {
                        name = "SomeConfig"
                    }
                }
            )
        })
    }

    //TODO bug when simple VO is after complex VO
}