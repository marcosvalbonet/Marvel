package com.marcosval.marvel.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcosval.marvel.domain.model.MarvelCharacterDetails
import com.marcosval.marvel.domain.usecase.GetCharacterDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private val _character = MutableStateFlow<MarvelCharacterDetails?>(null)
    val character: StateFlow<MarvelCharacterDetails?> get() = _character

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun getCharacterDetail(characterId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val characterDetail = getCharacterDetailUseCase(characterId)
                _character.value = characterDetail
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}