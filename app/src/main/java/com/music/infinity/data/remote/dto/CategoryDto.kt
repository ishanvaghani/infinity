package com.music.infinity.data.remote.dto

import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.Category
import kotlinx.serialization.SerialName

data class CategoryDto(
    @SerialName("href")
    val href: String,
    @SerialName("icons")
    val icons: List<Image>,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
) {
    fun toCategory(): Category {
        return Category(
            href, icons, id, name
        )
    }
}