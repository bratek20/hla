package pl.bratek20.hla.directory

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import pl.bratek20.hla.directory.api.Path
import pl.bratek20.hla.directory.impl.DirectoriesLogic

class DirectoriesTest {
    private val api = DirectoriesLogic()

    @Test
    fun shouldReadDirectory() {
        val result = api.readDirectory(Path("src/test/resources/directory"))
        assertDirectory(result) {
            name = "directory"
            hasDirectory = {
                name = "subdir"
                hasFile = {
                    name = "SomeFile.kt"
                    content = listOf(
                        "package directory",
                        "",
                        "class SomeFile {",
                        "}",
                        ""
                    )
                }
            }
        }
    }

    @Test
    fun shouldReadCorrectFileLength() {
        //TODO api.readFile
        val result = api.readDirectory(Path("src/test/resources/directory/subdir"))
        //TODO proper setup for this assertion
        assertThat(result.files[0].content.lines.size).isEqualTo(5)
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
            difference = "Different content for file dir1/file1 in line 1: `content1` != `content2`"
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

        val missingDirectory = directory {
            name = "dir"
        }
        assertCompareResult(api.compare(dir, missingDirectory)) {
            differences = listOf("Directory dir/dir1 not found in second directory")
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
            differences = listOf(
                "Directory dir/dir1 not found in second directory",
                "Directory dir/dir2 not found in first directory"
            )
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