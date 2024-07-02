package com.music.infinity.domain.model

data class Track(
    val album: Album,
    val artists: List<Artist>,
    val discNumber: Int,
    val durationMs: Long,
    val id: String,
    val name: String,
    val previewUrl: String?,
    val trackNumber: Int,
)