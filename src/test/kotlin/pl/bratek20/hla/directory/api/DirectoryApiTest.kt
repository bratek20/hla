package pl.bratek20.hla.directory.api

import org.junit.jupiter.api.Test
import pl.bratek20.hla.directory.assertCompareResult
import pl.bratek20.hla.directory.assertDirectory
import pl.bratek20.hla.directory.directory
import pl.bratek20.hla.directory.impl.DirectoryLogic

class DirectoryApiTest {
    private val api = DirectoryLogic()

    @Test
    fun shouldReadDirectory() {
        val result = api.readDirectory(Path("example/src/main/java/pl/bratek20/somemodule"))
        assertDirectory(result) {
            name = "somemodule"
            hasDirectory = {
                name = "api"
                hasFile = {
                    name = "SomeId.kt"
                    content = listOf(
                        "package pl.bratek20.somemodule.api",
                        "",
                        "data class SomeId(",
                        "    val value: String",
                        ")"
                    )
                }
            }
        }
    }

    @Test
    fun shouldCompareDirectoryFiles() {
        val dir1 = directory {
            name = "dir1"
            files = listOf {
                name = "file1"
                content = "content1"
            }
        }
        assertCompareResult(api.compare(dir1, dir1)) {
            same = true
        }

        val wrongFileName = directory {
            name = "dir1"
            files = listOf {
                name = "file2"
                content = "content1"
            }
        }
        assertCompareResult(api.compare(dir1, wrongFileName)) {
            differences = listOf(
                "File dir1/file1 not found in second directory",
                "File dir1/file2 not found in first directory"
            )
        }

        val wrongFileContent = directory {
            name = "dir1"
            files = listOf {
                name = "file1"
                content = "content2"
            }
        }
        assertCompareResult(api.compare(dir1, wrongFileContent)) {
            difference = "Different content for file dir1/file1 in line 1: content1 != content2"
        }
    }

    @Test
    fun shouldCompareNestedDirectories() {
        val dir = directory {
            name = "dir"
            directories = listOf {
                name = "dir1"
                files = listOf {
                    name = "file1"
                }
            }
        }
        assertCompareResult(api.compare(dir, dir)) {
            same = true
        }

        val wrongName = directory {
            name = "otherDir"
            directories = listOf {
                name = "dir1"
                files = listOf {
                    name = "file1"
                }
            }
        }
        assertCompareResult(api.compare(dir, wrongName)) {
            difference = "Different directory names: dir != otherDir"
        }

        val wrongNestedName = directory {
            name = "dir"
            directories = listOf {
                name = "dir2"
                files = listOf {
                    name = "file1"
                }
            }
        }
        assertCompareResult(api.compare(dir, wrongNestedName)) {
            differences = listOf("Different directory names: dir/dir1 != dir/dir2")
        }

        val wrongFile = directory {
            name = "dir"
            directories = listOf {
                name = "dir1"
                files = listOf {
                    name = "file2"
                }
            }
        }
        assertCompareResult(api.compare(dir, wrongFile)) {
            differences = listOf(
                "File dir/dir1/file1 not found in second directory",
                "File dir/dir1/file2 not found in first directory"
            )
        }
    }
}