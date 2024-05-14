package com.some.pkg.typesmodule.api

data class DateRangeProperty(
    private val from: String,
    private val to: String,
) {
    fun getFrom(): Date {
        return dateCreate(this.from)
    }

    fun getTo(): Date {
        return dateCreate(this.to)
    }
}