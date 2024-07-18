package com.music.infinity.presentation.genres

import androidx.lifecycle.viewModelScope
import com.music.infinity.common.launchIO
import com.music.infinity.data.local.SharedPrefs
import com.music.infinity.domain.usecase.GenreUseCase
import com.music.infinity.presentation.base.BaseViewModel
import com.music.infinity.presentation.genres.models.GenresAction
import com.music.infinity.presentation.genres.models.GenresState
import com.music.infinity.presentation.models.ScreenState

class GenresViewModel(
    private val genresUseCase: GenreUseCase
) : BaseViewModel<GenresState, GenresAction>() {

    override fun getInitialState(): ScreenState<GenresState> {
        return ScreenState(isLoading = true)
    }

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launchIO {
            val result = genresUseCase.getGenres()
            if (result.isRight()) {
                val genres = result.getOrNull()?.genres?.map {
                    it.replaceFirstChar(Char::titlecase)
                } ?: listOf()
                setDataState(GenresState(genres, listOf()))
            }
        }
    }

    fun saveGenres() {
        val genres = getCurrentData()?.selectedGenres
        if (genres.isNullOrEmpty().not()) {
            val genresString = genres!!.joinToString(separator = ",", limit = 3)
            SharedPrefs.setSelectedGenres(genresString.lowercase())
        }
    }

    fun isSelected(genre: String): Boolean {
        return getCurrentData()?.selectedGenres?.contains(genre) ?: false
    }

    fun genreClick(genre: String) {
        val selectedGenres = getCurrentData()?.selectedGenres ?: listOf()
        setDataState(
            getCurrentData()?.copy(
                selectedGenres = if (selectedGenres.contains(genre)) {
                    selectedGenres.minus(genre)
                } else {
                    selectedGenres.plus(genre)
                }
            )
        )
    }
}