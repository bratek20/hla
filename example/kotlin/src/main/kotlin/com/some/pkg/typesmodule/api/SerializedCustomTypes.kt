// DO NOT EDIT! Autogenerated by HLA tool

package com.some.pkg.typesmodule.api

data class SerializedDateRange(
    private val from: String,
    private val to: String,
) {
    fun toCustomType(): DateRange {
        return dateRangeCreate(
            from = dateCreate(from),
            to = dateCreate(to),
        )
    }

    companion object {
        fun fromCustomType(customType: DateRange): SerializedDateRange {
            return SerializedDateRange(
                from = dateGetValue(dateRangeGetFrom(customType)),
                to = dateGetValue(dateRangeGetTo(customType)),
            )
        }
    }
}