package com.marcosval.marvel.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcosval.marvel.domain.model.MarvelCharacter
import com.marcosval.marvel.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _characters = MutableStateFlow<List<MarvelCharacter>>(emptyList())
    val characters: StateFlow<List<MarvelCharacter>> get() = _characters

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private var currentPage = 0

    init {
        loadMoreCharacters()
    }

    fun loadMoreCharacters() {
        if (_isLoading.value) return

        viewModelScope.launch {
            _isLoading.value = true
            val offset = currentPage * 20

            try {
                val newCharacters = getCharactersUseCase(20, offset)
                _characters.value += newCharacters
                currentPage++
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}