package com.example.myapplication.shared

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.*

@Serializable
data class Item(
    @SerialName("id") var id: Long?,
    @SerialName("creationDate")
    @Serializable(with = LocalDateTimeSerializer::class)
    val creationDate: LocalDateTime
)