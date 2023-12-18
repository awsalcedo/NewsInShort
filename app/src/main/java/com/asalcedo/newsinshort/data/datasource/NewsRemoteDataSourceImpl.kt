package com.asalcedo.newsinshort.data.datasource

import com.asalcedo.newsinshort.data.api.NewsApiService
import com.asalcedo.newsinshort.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(private val api: NewsApiService) :
    NewsRemoteDataSource {
    override suspend fun getNewsHeadline(country: String): Response<NewsResponse> {
        return api.getNewsHeadline(country)
    }
}