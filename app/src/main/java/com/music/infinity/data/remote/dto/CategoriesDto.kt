package com.music.infinity.data.remote.dto

import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.Categories
import kotlinx.serialization.SerialName

data class CategoriesDto(
    @SerialName("href")
    val href: String,
    @SerialName("icons")
    val icons: List<Image>,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
) {
    fun toCategories(): Categories {
        return Categories(
            href, icons, id, name
        )
    }
}