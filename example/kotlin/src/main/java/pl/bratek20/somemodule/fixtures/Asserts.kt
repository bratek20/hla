package pl.bratek20.somemodule.fixtures

import org.assertj.core.api.Assertions.assertThat
import pl.bratek20.somemodule.api.*

data class ExpectedSomeClass(
    var id: String? = null,
    var amount: Int? = null,
)
fun assertSomeClass(given: SomeClass, expectedInit: ExpectedSomeClass.() -> Unit) {
    val expected = ExpectedSomeClass().apply(expectedInit)

    if (expected.id != null) {
        assertThat(given.id.value).isEqualTo(expected.id)
    }
    if (expected.amount != null) {
        assertThat(given.amount).isEqualTo(expected.amount)
    }
}

data class ExpectedSomeClass2(
    var id: String? = null,
    var enabled: Boolean? = null,
    var names: List<String>? = null,
)
fun assertSomeClass2(given: SomeClass2, expectedInit: ExpectedSomeClass2.() -> Unit) {
    val expected = ExpectedSomeClass2().apply(expectedInit)

    if (expected.id != null) {
        assertThat(given.id.value).isEqualTo(expected.id)
    }
    if (expected.enabled != null) {
        assertThat(given.enabled).isEqualTo(expected.enabled)
    }
    if (expected.names != null) {
        assertThat(given.names).isEqualTo(expected.names)
    }
}

