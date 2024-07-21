package com.music.infinity.presentation.search

import androidx.lifecycle.viewModelScope
import com.music.infinity.common.launchIO
import com.music.infinity.domain.usecase.SearchUseCase
import com.music.infinity.presentation.base.BaseViewModel
import com.music.infinity.presentation.models.ScreenState
import com.music.infinity.presentation.search.Models.SearchAction
import com.music.infinity.presentation.search.Models.SearchState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel

class SearchViewModel(
    private val searchUseCase: SearchUseCase,
) : BaseViewModel<SearchState, SearchAction>() {


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Handle $exception in CoroutineExceptionHandler")
    }

    override fun getInitialState(): ScreenState<SearchState> {
        return ScreenState(true)
    }

    public fun searchMusics(textSearch : String, type: String) {
        viewModelScope.cancel()
        viewModelScope.launchIO { scope ->
            val searchTextResponse = scope.async { searchUseCase.search(textSearch, type, offset) }
            val searchList = searchTextResponse.await()

            setDataState(
                SearchState(
                    searchList.getOrNull()
                )
            )

        }
    }
}