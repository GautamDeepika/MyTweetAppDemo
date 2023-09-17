package com.example.mytweetappdemo.repository

import com.example.mytweetappdemo.api.IApiService
import com.example.mytweetappdemo.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val iApiService: IApiService) {


    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweetList = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweetList: StateFlow<List<TweetListItem>>
        get() = _tweetList

    suspend fun getCategories() {
        val response = iApiService.getCategories()
        if (response.isSuccessful && response.body() != null) {
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getAllTweets(category: String) {
        val response = iApiService.getTweets(category)
        if (response.isSuccessful && response.body() != null) {
            _tweetList.emit(response.body()!!)
        }
    }


    suspend fun getTweets(category: String) {
        val response = iApiService.getTweets("tweets[?(@.category==\"$category\")]")
        if (response.isSuccessful && response.body() != null) {
            _tweetList.emit(response.body()!!)
        }
    }
}