package com.example.playgroundapp.presentation.di.helpers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModelProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val vmProvider: Provider<ViewModel> = getViewModelProvider(modelClass)
        return try {
            vmProvider.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun <T : ViewModel> getViewModelProvider(modelClass: Class<T>): Provider<ViewModel> {
        val provider = viewModelProviders[modelClass] ?: findAssignableViewModelProvider(modelClass)
        return provider ?: throw IllegalArgumentException("unknown model class $modelClass")
    }

    private fun <T> findAssignableViewModelProvider(vmClass: Class<T>): Provider<ViewModel>? {
        val entries = viewModelProviders.entries
        val vmProvider = entries.firstOrNull { vmClass.isAssignableFrom(it.key) }
        return vmProvider?.value
    }
}