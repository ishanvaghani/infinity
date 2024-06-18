package com.music.infinity.domain.model

data class CategoriesList(
    val href: String,
    val limit: Long,
    val next: String,
    val offset: Long,
    val previous: String?,
    val total: Long,
    val categories: List<Category>,
)
