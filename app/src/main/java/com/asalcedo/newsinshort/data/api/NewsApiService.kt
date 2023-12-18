package com.asalcedo.newsinshort.data.api

import com.asalcedo.newsinshort.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    //GET https://newsapi.org/v2/top-headlines?country=us&apiKey=f0e3e9d0551146918183e5d2d44443eb
    @GET("v2/top-headlines")
    suspend fun getNewsHeadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "f0e3e9d0551146918183e5d2d44443eb"
    ): Response<NewsResponse>

}