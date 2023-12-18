package com.asalcedo.newsinshort.ui.repository

import com.asalcedo.newsinshort.data.datasource.NewsRemoteDataSource
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsRemoteDataSource: NewsRemoteDataSource) {
}