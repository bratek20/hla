package com.some.pkg.typesmodule.web

import com.some.pkg.typesmodule.api.*

data class DateRangeDto(
    val from: String,
    val to: String,
) {
    fun toApi(): DateRange {
        return dateRangeCreate(
            from = dateCreate(from),
            to = dateCreate(to),
        )
    }

    companion object {
        fun fromApi(api: DateRange): DateRangeDto {
            return DateRangeDto(
                from = dateGetValue(dateRangeGetFrom(api)),
                to = dateGetValue(dateRangeGetTo(api)),
            )
        }
    }
}