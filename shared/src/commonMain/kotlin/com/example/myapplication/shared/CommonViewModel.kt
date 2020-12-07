package com.example.myapplication.shared

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class CommonViewModel {

    fun createDateModel() {
        val item = Item(
            id = null,
            Clock.System.now().toLocalDateTime(TimeZone.UTC)
        )
    }

    companion object {
        fun create() =
            CommonViewModel()
    }
}