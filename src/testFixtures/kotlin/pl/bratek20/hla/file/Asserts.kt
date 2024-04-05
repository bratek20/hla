package pl.bratek20.hla.file

import org.assertj.core.api.Assertions.assertThat

data class ExpectedFile(
    var name: String? = null,
    var content: String? = null
)
fun assertFile(given: File, expectedOv: ExpectedFile.() -> Unit) {
    val expected = ExpectedFile().apply(expectedOv)

    if (expected.name != null) {
        assertThat(given.name).isEqualTo(expected.name)
    }

    if (expected.content != null) {
        assertThat(given.content).isEqualTo(expected.content)
    }
}