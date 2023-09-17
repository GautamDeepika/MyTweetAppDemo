package com.example.mytweetappdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytweetappdemo.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryVM @Inject constructor(private val repository: TweetRepository) : ViewModel() {

    val categories: StateFlow<List<String>>
        get() = repository.categories


    init {
        viewModelScope.launch {
            repository.getCategories()
        }
    }
}