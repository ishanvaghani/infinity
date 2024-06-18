package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.CategoriesList
import kotlinx.serialization.SerialName

data class CategoriesListDto(
    @SerialName("href")
    val href: String,
    @SerialName("limit")
    val limit: Long,
    @SerialName("next")
    val next: String,
    @SerialName("offset")
    val offset: Long,
    @SerialName("previous")
    val previous: String?,
    @SerialName("total")
    val total: Long,
    @SerialName("items")
    val categories: List<CategoryDto>,
) {

    fun toCategoriesList(): CategoriesList {
        return CategoriesList(
            href,
            limit,
            next,
            offset,
            previous,
            total,
            categories.map { it.toCategory() }
        )
    }
}
