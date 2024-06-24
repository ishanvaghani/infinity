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

abstract class BaseViewModel<T, U> : ViewModel() {

    private val _uiState: MutableStateFlow<ScreenState<T>> by lazy {
        MutableStateFlow(ScreenState(isLoading = true, data = null))
    }
    val uiState: StateFlow<ScreenState<T>> by lazy {
        _uiState.asStateFlow()
    }

    private val _uiAction: MutableSharedFlow<U> by lazy {
        MutableSharedFlow()
    }
    val uiAction: SharedFlow<U> by lazy {
        _uiAction.asSharedFlow()
    }

    fun sendAction(action: U) = viewModelScope.launch(Dispatchers.Main) {
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

    fun setScreenState(state: ScreenState<T>) {
        _uiState.value = state
    }

    fun getCurrentState(): ScreenState<T> {
        return if (_uiState.value.data == null) getInitialState() else _uiState.value
    }

    abstract fun getInitialState(): ScreenState<T>
}