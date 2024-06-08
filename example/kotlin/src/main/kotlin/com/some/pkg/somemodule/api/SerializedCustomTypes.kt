// DO NOT EDIT! Autogenerated by HLA tool

package com.some.pkg.somemodule.api

import com.some.pkg.othermodule.api.*
import com.some.pkg.typesmodule.api.*

data class SerializedDateRangeWrapper(
    private val range: SerializedDateRange,
) {
    fun toCustomType(): DateRangeWrapper {
        return dateRangeWrapperCreate(
            range = range.toCustomType(),
        )
    }

    companion object {
        fun fromCustomType(customType: DateRangeWrapper): SerializedDateRangeWrapper {
            return SerializedDateRangeWrapper(
                range = SerializedDateRange.fromCustomType(dateRangeWrapperGetRange(customType)),
            )
        }
    }
}