package com.asalcedo.newsinshort.data.datasource

import com.asalcedo.newsinshort.data.entity.NewsResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getNewsHeadline(country: String): Response<NewsResponse>
}