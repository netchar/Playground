package com.example.playgroundapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playgroundapp.domain.entity.Author
import com.example.playgroundapp.domain.interactors.AuthorInteractor
import kotlinx.coroutines.launch

class HomeViewModel(
    private val skeletonInteractor: AuthorInteractor
) : ViewModel() {
    private val mutableItems = MutableLiveData<List<Author>>()
    private val mutableLoading = MutableLiveData<Boolean>()

    val items: LiveData<List<Author>> = mutableItems
    val loading: LiveData<Boolean> = mutableLoading

    init {
        fetchItems()
    }

    fun fetchItems() {
        viewModelScope.launch {
            mutableLoading.value = true
            mutableItems.value = skeletonInteractor.getItems()
            mutableLoading.value = false
        }
    }

    fun refresh() {
        fetchItems()
    }
}