package com.music.infinity.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.music.infinity.presentation.models.ScreenError
import com.music.infinity.presentation.models.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, A> : ViewModel() {

    private val _uiState: MutableStateFlow<ScreenState<S>> by lazy {
        MutableStateFlow(ScreenState(isLoading = true, data = null))
    }
    val uiState: StateFlow<ScreenState<S>> by lazy {
        _uiState.asStateFlow()
    }

    private val _uiAction: MutableSharedFlow<A> by lazy {
        MutableSharedFlow()
    }
    val uiAction: SharedFlow<A> by lazy {
        _uiAction.asSharedFlow()
    }

    fun sendAction(action: A) = viewModelScope.launch(Dispatchers.Main) {
        _uiAction.emit(action)
    }

    fun setLoadingState() {
        _uiState.update {
            getCurrentState().copy(isLoading = true)
        }
    }

    fun hideLoadingState() {
        _uiState.update {
            getCurrentState().copy(isLoading = false)
        }
    }

    fun setErrorState(message: String?) {
        _uiState.update {
            getCurrentState().copy(
                isLoading = false,
                error = ScreenError(message = message ?: "")
            )
        }
    }

    fun setDataState(data: S?) {
        _uiState.update {
            getCurrentState().copy(
                isLoading = false,
                data = data
            )
        }
    }

    fun getCurrentState(): ScreenState<S> {
        return if (_uiState.value.data == null) getInitialState() else _uiState.value
    }

    fun getCurrentData(): S? {
        return getCurrentState().data
    }

    fun isLoading(): Boolean {
        return getCurrentState().isLoading
    }

    fun getError(): ScreenError? {
        return getCurrentState().error
    }

    abstract fun getInitialState(): ScreenState<S>
}