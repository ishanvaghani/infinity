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
                setScreenState(
                    getCurrentState().copy(
                        isLoading = false,
                        data = GenresState(genres, listOf())
                    )
                )
            }
        }
    }

    fun saveGenres() {
        val genres = getCurrentState().data?.selectedGenres
        if (genres.isNullOrEmpty().not()) {
            val genresString = genres!!.joinToString(", ")
            SharedPrefs.setSelectedGenres(genresString)
        }
    }

    fun isSelected(genre: String): Boolean {
        return getCurrentState().data?.selectedGenres?.contains(genre) ?: false
    }

    fun genreClick(genre: String) {
        val selectedGenres = getCurrentState().data?.selectedGenres ?: listOf()
        setScreenState(
            getCurrentState().copy(
                data = getCurrentState().data?.copy(
                    selectedGenres = if (selectedGenres.contains(genre)) {
                        selectedGenres.minus(genre)
                    } else {
                        selectedGenres.plus(genre)
                    }
                )
            )
        )
    }
}