// DO NOT EDIT! Autogenerated by HLA tool

package com.some.pkg.somemodule.fixtures

import com.some.pkg.othermodule.api.*
import com.some.pkg.othermodule.fixtures.*
import com.some.pkg.typesmodule.api.*
import com.some.pkg.typesmodule.fixtures.*

import com.some.pkg.somemodule.api.*

fun diffSomeId(given: SomeId, expected: String, path: String = ""): String {
    if (given.value != expected) { return "${path}value ${given.value} != ${expected}" }
    return ""
}

data class ExpectedDateRangeWrapper(
    var range: (ExpectedDateRange.() -> Unit)? = null,
)
fun diffDateRangeWrapper(given: DateRangeWrapper, expectedInit: ExpectedDateRangeWrapper.() -> Unit, path: String = ""): String {
    val expected = ExpectedDateRangeWrapper().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.range?.let {
        if (diffDateRange(dateRangeWrapperGetRange(given), it) != "") { result.add(diffDateRange(dateRangeWrapperGetRange(given), it, "${path}range.")) }
    }

    return result.joinToString("\n")
}

data class ExpectedSomeProperty(
    var other: (ExpectedOtherProperty.() -> Unit)? = null,
)
fun diffSomeProperty(given: SomeProperty, expectedInit: ExpectedSomeProperty.() -> Unit, path: String = ""): String {
    val expected = ExpectedSomeProperty().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.other?.let {
        if (diffOtherProperty(given.other, it) != "") { result.add(diffOtherProperty(given.other, it, "${path}other.")) }
    }

    return result.joinToString("\n")
}

data class ExpectedSomeClass(
    var id: String? = null,
    var amount: Int? = null,
)
fun diffSomeClass(given: SomeClass, expectedInit: ExpectedSomeClass.() -> Unit, path: String = ""): String {
    val expected = ExpectedSomeClass().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.id?.let {
        if (diffSomeId(given.id, it) != "") { result.add(diffSomeId(given.id, it, "${path}id.")) }
    }

    expected.amount?.let {
        if (given.amount != it) { result.add("${path}amount ${given.amount} != ${it}") }
    }

    return result.joinToString("\n")
}

data class ExpectedSomeClass2(
    var id: String? = null,
    var names: List<String>? = null,
    var ids: List<String>? = null,
    var enabled: Boolean? = null,
)
fun diffSomeClass2(given: SomeClass2, expectedInit: ExpectedSomeClass2.() -> Unit, path: String = ""): String {
    val expected = ExpectedSomeClass2().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.id?.let {
        if (diffSomeId(given.id, it) != "") { result.add(diffSomeId(given.id, it, "${path}id.")) }
    }

    expected.names?.let {
        if (given.names.size != it.size) { result.add("${path}names size ${given.names.size} != ${it.size}") }
        given.names.forEachIndexed { idx, entry -> if (entry != it[idx]) { result.add("${path}names[$idx] $entry != ${it[idx]}") } }
    }

    expected.ids?.let {
        if (given.ids.size != it.size) { result.add("${path}ids size ${given.ids.size} != ${it.size}") }
        given.ids.forEachIndexed { idx, entry -> if (diffSomeId(entry, it[idx]) != "") { result.add(diffSomeId(entry, it[idx], "${path}ids[$idx].")) } }
    }

    expected.enabled?.let {
        if (given.enabled != it) { result.add("${path}enabled ${given.enabled} != ${it}") }
    }

    return result.joinToString("\n")
}

data class ExpectedSomeClass3(
    var class2Object: (ExpectedSomeClass2.() -> Unit)? = null,
    var someEnum: SomeEnum? = null,
    var class2List: List<(ExpectedSomeClass2.() -> Unit)>? = null,
)
fun diffSomeClass3(given: SomeClass3, expectedInit: ExpectedSomeClass3.() -> Unit, path: String = ""): String {
    val expected = ExpectedSomeClass3().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.class2Object?.let {
        if (diffSomeClass2(given.class2Object, it) != "") { result.add(diffSomeClass2(given.class2Object, it, "${path}class2Object.")) }
    }

    expected.someEnum?.let {
        if (given.someEnum != it) { result.add("${path}someEnum ${given.someEnum} != ${it}") }
    }

    expected.class2List?.let {
        if (given.class2List != it) { result.add("${path}class2List ${given.class2List} != ${it}") }
    }

    return result.joinToString("\n")
}

data class ExpectedSomeClass4(
    var otherId: Int? = null,
    var otherClass: (ExpectedOtherClass.() -> Unit)? = null,
    var otherIdList: List<Int>? = null,
    var otherClassList: List<(ExpectedOtherClass.() -> Unit)>? = null,
)
fun diffSomeClass4(given: SomeClass4, expectedInit: ExpectedSomeClass4.() -> Unit, path: String = ""): String {
    val expected = ExpectedSomeClass4().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.otherId?.let {
        if (diffOtherId(given.otherId, it) != "") { result.add(diffOtherId(given.otherId, it, "${path}otherId.")) }
    }

    expected.otherClass?.let {
        if (diffOtherClass(given.otherClass, it) != "") { result.add(diffOtherClass(given.otherClass, it, "${path}otherClass.")) }
    }

    expected.otherIdList?.let {
        if (given.otherIdList != it) { result.add("${path}otherIdList ${given.otherIdList} != ${it}") }
    }

    expected.otherClassList?.let {
        if (given.otherClassList != it) { result.add("${path}otherClassList ${given.otherClassList} != ${it}") }
    }

    return result.joinToString("\n")
}

data class ExpectedSomeClass5(
    var date: String? = null,
    var dateRange: (ExpectedDateRange.() -> Unit)? = null,
    var dateRangeWrapper: (ExpectedDateRangeWrapper.() -> Unit)? = null,
    var someProperty: (ExpectedSomeProperty.() -> Unit)? = null,
    var otherProperty: (ExpectedOtherProperty.() -> Unit)? = null,
)
fun diffSomeClass5(given: SomeClass5, expectedInit: ExpectedSomeClass5.() -> Unit, path: String = ""): String {
    val expected = ExpectedSomeClass5().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.date?.let {
        if (diffDate(given.date, it) != "") { result.add(diffDate(given.date, it, "${path}date.")) }
    }

    expected.dateRange?.let {
        if (diffDateRange(given.dateRange, it) != "") { result.add(diffDateRange(given.dateRange, it, "${path}dateRange.")) }
    }

    expected.dateRangeWrapper?.let {
        if (diffDateRangeWrapper(given.dateRangeWrapper, it) != "") { result.add(diffDateRangeWrapper(given.dateRangeWrapper, it, "${path}dateRangeWrapper.")) }
    }

    expected.someProperty?.let {
        if (diffSomeProperty(given.someProperty, it) != "") { result.add(diffSomeProperty(given.someProperty, it, "${path}someProperty.")) }
    }

    expected.otherProperty?.let {
        if (diffOtherProperty(given.otherProperty, it) != "") { result.add(diffOtherProperty(given.otherProperty, it, "${path}otherProperty.")) }
    }

    return result.joinToString("\n")
}

data class ExpectedSomeClass6(
    var someClassOptEmpty: Boolean? = null,
    var someClassOpt: (ExpectedSomeClass.() -> Unit)? = null,
    var optStringEmpty: Boolean? = null,
    var optString: String? = null,
    var sameClassList: List<(ExpectedSomeClass6.() -> Unit)>? = null,
)
fun diffSomeClass6(given: SomeClass6, expectedInit: ExpectedSomeClass6.() -> Unit, path: String = ""): String {
    val expected = ExpectedSomeClass6().apply(expectedInit)
    val result: MutableList<String> = mutableListOf()

    expected.someClassOptEmpty?.let {
        if ((given.someClassOpt == null) != it) { result.add("${path}someClassOpt empty ${(given.someClassOpt == null) != it} != ${it}") }
    }

    expected.someClassOpt?.let {
        if (diffSomeClass(given.someClassOpt!!, it) != "") { result.add(diffSomeClass(given.someClassOpt!!, it, "${path}someClassOpt.")) }
    }

    expected.optStringEmpty?.let {
        if ((given.optString == null) != it) { result.add("${path}optString empty ${(given.optString == null) != it} != ${it}") }
    }

    expected.optString?.let {
        if (given.optString!! != it) { result.add("${path}optString ${given.optString!!} != ${it}") }
    }

    expected.sameClassList?.let {
        if (given.sameClassList != it) { result.add("${path}sameClassList ${given.sameClassList} != ${it}") }
    }

    return result.joinToString("\n")
}