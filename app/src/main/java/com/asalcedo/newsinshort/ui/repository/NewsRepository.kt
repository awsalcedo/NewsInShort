package com.asalcedo.newsinshort.ui.repository

import com.asalcedo.newsinshort.data.datasource.NewsRemoteDataSource
import com.asalcedo.newsinshort.data.entity.NewsResponse
import com.asalcedo.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsRemoteDataSource: NewsRemoteDataSource) {

    /*suspend fun getNewsHeadline(country: String): Response<NewsResponse> {
        return newsRemoteDataSource.getNewsHeadline(country)
    }*/

    suspend fun getNewsHeadline(country: String): Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())
            val response = newsRemoteDataSource.getNewsHeadline(country)
            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error fetching news data"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e?.localizedMessage ?: "Some error in flow"))
        }
    }

}