package com.example.playgroundapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playgroundapp.domain.common.Result
import com.example.playgroundapp.domain.entity.Character
import com.example.playgroundapp.domain.interactors.CharacterInteractor
import kotlinx.coroutines.launch

class HomeViewModel(
    private val skeletonInteractor: CharacterInteractor
) : ViewModel() {
    private val mutableErrorMessage = MutableLiveData<String>()
    private val mutableItems = MutableLiveData<List<Character>>()
    private val mutableLoading = MutableLiveData<Boolean>()

    val items: LiveData<List<Character>> = mutableItems
    val loading: LiveData<Boolean> = mutableLoading
    val errorMessage: LiveData<String> = mutableErrorMessage

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            mutableLoading.value = true
            when (val response = skeletonInteractor.getCharacters()) {
                is Result.Success -> mutableItems.value = response.data
                is Result.Error -> mutableErrorMessage.value = "Something went wrong"
            }
            mutableLoading.value = false
        }
    }

    fun refresh() {
        fetchItems()
    }
}