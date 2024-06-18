package com.music.infinity.domain.model

data class Track(
    val artists: List<Artist>,
    val discNumber: Int,
    val durationMs: Int,
    val id: String,
    val name: String,
    val previewUrl: String,
    val trackNumber: Int,
)