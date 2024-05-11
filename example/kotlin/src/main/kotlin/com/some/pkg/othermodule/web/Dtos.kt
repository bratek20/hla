package com.some.pkg.othermodule.web

import com.some.pkg.othermodule.api.*

data class OtherClassDto(
    val id: String,
    val amount: Int,
) {
    fun toApi(): OtherClass {
        return OtherClass(
            id = OtherId(id),
            amount = amount,
        )
    }

    companion object {
        fun fromApi(api: OtherClass): OtherClassDto {
            return OtherClassDto(
                id = api.id.value,
                amount = api.amount,
            )
        }
    }
}