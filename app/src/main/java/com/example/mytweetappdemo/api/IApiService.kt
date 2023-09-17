package com.example.mytweetappdemo.api

import com.example.mytweetappdemo.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface IApiService {


    @GET("/v3/b/65046ee3e4033326cbd83b97?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<TweetListItem>>   // for dynamic header


    @GET("/v3/b/65046ee3e4033326cbd83b97?meta=false")
    @Headers("X-JSON-Path:tweets..category") // Remove the space here
    suspend fun getCategories(): Response<List<String>>

}