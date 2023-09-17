package com.example.mytweetappdemo.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytweetappdemo.models.TweetListItem
import com.example.mytweetappdemo.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val repository: TweetRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val tweets: StateFlow<List<TweetListItem>>
        get() = repository.tweetList


    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "motivation"
            repository.getTweets(category)
        }
    }
}