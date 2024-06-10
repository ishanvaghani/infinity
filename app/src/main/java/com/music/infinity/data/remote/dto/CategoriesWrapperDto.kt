package com.music.infinity.data.remote.dto

import kotlinx.serialization.SerialName

data class CategoriesWrapperDto(
    @SerialName("href")
    val href: String,
    @SerialName("limit")
    val limit: Long,
    @SerialName("next")
    val next: String,
    @SerialName("offset")
    val offset: Long,
    @SerialName("previous")
    val previous: Any?,
    @SerialName("total")
    val total: Long,
    @SerialName("items")
    val categoriesDto: List<CategoriesDto>,
)
