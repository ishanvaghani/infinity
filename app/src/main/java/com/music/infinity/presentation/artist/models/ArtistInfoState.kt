package com.music.infinity.presentation.artist.models

import androidx.compose.runtime.Stable
import com.music.infinity.domain.model.AlbumList
import com.music.infinity.domain.model.Artist
import com.music.infinity.domain.model.ArtistAlbum
import com.music.infinity.domain.model.ArtistInfo
import com.music.infinity.domain.model.ArtistList
import com.music.infinity.domain.model.RelatedArtist

@Stable
class ArtistInfoState(
    val artistInfo : ArtistInfo?,
    val artistAlbum : List<ArtistAlbum>?,
    val relatedArtist : RelatedArtist?
)
