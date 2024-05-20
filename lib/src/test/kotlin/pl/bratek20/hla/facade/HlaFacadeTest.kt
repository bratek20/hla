package pl.bratek20.hla.facade

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import pl.bratek20.architecture.context.someContextBuilder
import pl.bratek20.architecture.properties.impl.PropertiesModule
import pl.bratek20.architecture.properties.sources.inmemory.InMemoryPropertiesSource
import pl.bratek20.architecture.properties.sources.inmemory.InMemoryPropertiesSourceModule
import pl.bratek20.hla.directory.DirectoriesMock
import pl.bratek20.hla.directory.api.Directory
import pl.bratek20.hla.directory.api.Path
import pl.bratek20.hla.directory.context.DirectoriesMocks
import pl.bratek20.hla.directory.impl.DirectoriesLogic
import pl.bratek20.hla.facade.api.*
import pl.bratek20.hla.facade.context.FacadeImpl
import pl.bratek20.hla.facade.fixtures.hlaProperties
import java.util.stream.Stream

class HlaFacadeTest {
    data class TestPaths(
        val exampleMainPath: String,
        val exampleTestFixturesPath: String,
        val expectedMainPathSuffix: String,
        val expectedTestFixturesPathSuffix: String
    )

    class MyArgumentsProvider : ArgumentsProvider {
        private fun kotlinTestPaths(packageName: String): TestPaths {
            return TestPaths(
                exampleMainPath = "../example/kotlin/src/main/kotlin/com/some/pkg/$packageName",
                exampleTestFixturesPath = "../example/kotlin/src/testFixtures/kotlin/com/some/pkg/$packageName",
                expectedMainPathSuffix = "/src/main/kotlin/com/some/pkg",
                expectedTestFixturesPathSuffix = "/src/testFixtures/kotlin/com/some/pkg"
            )
        }

        private fun typescriptTestPaths(moduleName: String): TestPaths {
            return TestPaths(
                exampleMainPath = "../example/typescript/main/$moduleName",
                exampleTestFixturesPath = "../example/typescript/test/$moduleName",
                expectedMainPathSuffix = "/main",
                expectedTestFixturesPathSuffix = "/test"
            )
        }

        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(
                    "OtherModule",
                    ModuleLanguage.KOTLIN,
                    kotlinTestPaths("othermodule")
                ),
                Arguments.of(
                    "OtherModule",
                    ModuleLanguage.TYPE_SCRIPT,
                    typescriptTestPaths("OtherModule")
                ),
                Arguments.of(
                    "SomeModule",
                    ModuleLanguage.KOTLIN,
                    kotlinTestPaths("somemodule")
                ),
                Arguments.of(
                    "SomeModule",
                    ModuleLanguage.TYPE_SCRIPT,
                    typescriptTestPaths("SomeModule")
                ),
                Arguments.of(
                    "TypesModule",
                    ModuleLanguage.KOTLIN,
                    kotlinTestPaths("typesmodule")
                ),
                Arguments.of(
                    "TypesModule",
                    ModuleLanguage.TYPE_SCRIPT,
                    typescriptTestPaths("TypesModule")
                ),
            )
        }
    }

    data class SetupResult(
        val directoriesMock: DirectoriesMock,
        val facade: HlaFacade
    )
    private fun setup(): SetupResult {
        val context = someContextBuilder()
            .withModules(
                DirectoriesMocks(),

                PropertiesModule(),
                InMemoryPropertiesSourceModule(),

                FacadeImpl(),
            )
            .build()

        val directoriesMock = context.get(DirectoriesMock::class.java)
        val propertiesSource = context.get(InMemoryPropertiesSource::class.java)

        propertiesSource.set(
            PROPERTIES_KEY,
            hlaProperties {
                generateWeb = true
                kotlin = {
                    rootPackage = "com.some.pkg"
                }
                typeScript = {
                    srcPath = "main"
                    testPath = "test"
                }
            }
        )

        val facade = context.get(HlaFacade::class.java)

        return SetupResult(directoriesMock, facade)
    }

    @ParameterizedTest(name = "{0} ({1})")
    @ArgumentsSource(MyArgumentsProvider::class)
    fun `should start module`(
        moduleName: String,
        lang: ModuleLanguage,
        paths: TestPaths
    ) {
        //given
        val (directoriesMock, facade) = setup()

        val hlaFolderPath = Path("../example/hla")
        val projectPath = Path("some/project/path")

        //when
        facade.startModule(
            ModuleOperationArgs(
                moduleName = ModuleName(moduleName),
                language = lang,
                hlaFolderPath = hlaFolderPath,
                projectPath = projectPath
            )
        )

        //then
        directoriesMock.assertWriteCount(2)
        val mainDirectory = directoriesMock.assertWriteAndGetDirectory(
            1,
            "some/project/path" + paths.expectedMainPathSuffix
        )
        val testFixturesDirectory = directoriesMock.assertWriteAndGetDirectory(
            2,
            "some/project/path" + paths.expectedTestFixturesPathSuffix
        )

        assertWrittenDirectoryWithExample(mainDirectory, paths.exampleMainPath)
        assertWrittenDirectoryWithExample(testFixturesDirectory, paths.exampleTestFixturesPath)
    }

    private fun assertWrittenDirectoryWithExample(writtenDirectory: Directory, examplePath: String ) {
        val directories = DirectoriesLogic()
        val exampleDirectory = directories.readDirectory(Path(examplePath))

        val compareResult = directories.compare(writtenDirectory, exampleDirectory)
        val failMessage = "${compareResult.differences.size} differences found!\n" +
                compareResult.differences.joinToString("\n")

        assertThat(compareResult.same)
            .withFailMessage(failMessage)
            .isTrue()
    }

    @Test
    fun `should update module`() {
        //given
        val (directoriesMock, facade) = setup()

        val hlaFolderPath = Path("../example/hla")
        val projectPath = Path("some/project/path")

        val args = ModuleOperationArgs(
            moduleName = ModuleName("SomeModule"),
            language = ModuleLanguage.KOTLIN,
            hlaFolderPath = hlaFolderPath,
            projectPath = projectPath
        )
        facade.startModule(args)

        val expectedFilesToSkipUpdate = setOf(
            "api/CustomTypes",
            "api/CustomTypesMapper",
        )

        val expectedDirectoriesToSkipUpdate = setOf(
            "context",
            "impl"
        )

        //when
        facade.updateModule(args)

        //then
        directoriesMock.assertWriteCount(4)
        val mainDirectoryStart = directoriesMock.assertWriteAndGetDirectory(
            1,
            "some/project/path/src/main/kotlin/com/some/pkg"
        )
        val testFixturesDirectoryStart = directoriesMock.assertWriteAndGetDirectory(
            2,
            "some/project/path/src/testFixtures/kotlin/com/some/pkg"
        )

        val mainDirectoryUpdate = directoriesMock.assertWriteAndGetDirectory(
            3,
            "some/project/path/src/main/kotlin/com/some/pkg"
        )

        val testFixturesDirectoryUpdate = directoriesMock.assertWriteAndGetDirectory(
            4,
            "some/project/path/src/testFixtures/kotlin/com/some/pkg"
        )

        val mainCompareResult = DirectoriesLogic().compare(mainDirectoryStart, mainDirectoryUpdate)
        val testFixturesCompareResult = DirectoriesLogic().compare(testFixturesDirectoryStart, testFixturesDirectoryUpdate)

        val expectedMainDifference = expectedFilesToSkipUpdate.map {
                "File somemodule/$it.kt not found in second directory"
            } + expectedDirectoriesToSkipUpdate.map {
                "Directory somemodule/$it not found in second directory"
            }

        assertThat(mainCompareResult.differences).containsExactlyInAnyOrderElementsOf(
            expectedMainDifference
        )

        assertThat(testFixturesCompareResult.differences).isEmpty()
    }
}