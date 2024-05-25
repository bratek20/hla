// DO NOT EDIT! Autogenerated by HLA tool

package com.some.pkg.somemodule.fixtures

import com.some.pkg.othermodule.api.*
import com.some.pkg.othermodule.fixtures.*
import com.some.pkg.typesmodule.api.*
import com.some.pkg.typesmodule.fixtures.*

import com.some.pkg.somemodule.api.*

data class DateRangeWrapperDef(
    var range: (DateRangeDef.() -> Unit) = {},
)
fun dateRangeWrapper(init: DateRangeWrapperDef.() -> Unit = {}): DateRangeWrapper {
    val def = DateRangeWrapperDef().apply(init)
    return dateRangeWrapperCreate(
        range = dateRange(def.range),
    )
}

data class SomePropertyDef(
    var other: (OtherPropertyDef.() -> Unit) = {},
)
fun someProperty(init: SomePropertyDef.() -> Unit = {}): SomeProperty {
    val def = SomePropertyDef().apply(init)
    return SomeProperty(
        other = otherProperty(def.other),
    )
}

data class SomeClassDef(
    var id: String = "someValue",
    var amount: Int = 0,
)
fun someClass(init: SomeClassDef.() -> Unit = {}): SomeClass {
    val def = SomeClassDef().apply(init)
    return SomeClass(
        id = SomeId(def.id),
        amount = def.amount,
    )
}

data class SomeClass2Def(
    var id: String = "someValue",
    var enabled: Boolean = false,
    var names: List<String> = emptyList(),
    var ids: List<String> = emptyList(),
)
fun someClass2(init: SomeClass2Def.() -> Unit = {}): SomeClass2 {
    val def = SomeClass2Def().apply(init)
    return SomeClass2(
        id = SomeId(def.id),
        enabled = def.enabled,
        names = def.names,
        ids = def.ids.map { it -> SomeId(it) },
    )
}

data class SomeClass3Def(
    var class2Object: (SomeClass2Def.() -> Unit) = {},
    var class2List: List<(SomeClass2Def.() -> Unit)> = emptyList(),
    var someEnum: SomeEnum = SomeEnum.VALUE_A,
)
fun someClass3(init: SomeClass3Def.() -> Unit = {}): SomeClass3 {
    val def = SomeClass3Def().apply(init)
    return SomeClass3(
        class2Object = someClass2(def.class2Object),
        class2List = def.class2List.map { it -> someClass2(it) },
        someEnum = def.someEnum,
    )
}

data class SomeClass4Def(
    var otherId: Int = 0,
    var otherClass: (OtherClassDef.() -> Unit) = {},
    var otherIdList: List<Int> = emptyList(),
    var otherClassList: List<(OtherClassDef.() -> Unit)> = emptyList(),
)
fun someClass4(init: SomeClass4Def.() -> Unit = {}): SomeClass4 {
    val def = SomeClass4Def().apply(init)
    return SomeClass4(
        otherId = OtherId(def.otherId),
        otherClass = otherClass(def.otherClass),
        otherIdList = def.otherIdList.map { it -> OtherId(it) },
        otherClassList = def.otherClassList.map { it -> otherClass(it) },
    )
}

data class SomeClass5Def(
    var date: String = "01/01/1970 00:00",
    var dateRange: (DateRangeDef.() -> Unit) = {},
    var dateRangeWrapper: (DateRangeWrapperDef.() -> Unit) = {},
    var someProperty: (SomePropertyDef.() -> Unit) = {},
    var otherProperty: (OtherPropertyDef.() -> Unit) = {},
)
fun someClass5(init: SomeClass5Def.() -> Unit = {}): SomeClass5 {
    val def = SomeClass5Def().apply(init)
    return SomeClass5(
        date = dateCreate(def.date),
        dateRange = dateRange(def.dateRange),
        dateRangeWrapper = dateRangeWrapper(def.dateRangeWrapper),
        someProperty = someProperty(def.someProperty),
        otherProperty = otherProperty(def.otherProperty),
    )
}