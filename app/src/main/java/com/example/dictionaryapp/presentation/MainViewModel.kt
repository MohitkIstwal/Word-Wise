package com.example.dictionaryapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.domain.repository.DictionaryRepository
import com.example.dictionaryapp.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {

    private val _mainState = MutableStateFlow(MainState())// private because we can't modify within class
    val mainState = _mainState.asStateFlow()// exposed to the rest of the app

    private var searchJob: Job? = null
    // earchJob is like a way to manage and keep track of ongoing searches. It helps cancel the old search when a new one starts, so the app doesn’t waste time on outdated information and always focuses on the most recent search request.

    init {
        _mainState.update {
            it.copy(searchWord = "")
            // set default value here
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            loadWordResult()
        }
    }

    fun onEvent(mainUiEvent: MainUiEvents) {
        when (mainUiEvent) {
            MainUiEvents.OnSearchClick -> {
                searchJob?.cancel()
                // if the button is clicked it cancels any ongoing search
                // after that it start new cooroutine
                searchJob = viewModelScope.launch {
                    loadWordResult()
                }
            }

            is MainUiEvents.OnSearchWordChange -> {
                _mainState.update {
                    it.copy(
                        searchWord = mainUiEvent.newWord.lowercase()
                    )
                }
            }

        }
    }

    private fun loadWordResult() {
        viewModelScope.launch {
            dictionaryRepository.getWordResult(
                mainState.value.searchWord// here the value of word parameter has been passed
            ).collect { result ->
                when (result) {
                    is Results.Error -> {
                        result.message?.let { message->
                            _mainState.update {
                                it.copy(
                                    error = message
                                )
                            }
                        }
                    }
                    is Results.Loading -> {
                        _mainState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is Results.Success -> {
                        result.data?.let { wordItem ->
                            _mainState.update {
                                it.copy(
                                    wordItem = wordItem
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}