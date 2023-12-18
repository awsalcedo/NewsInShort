package com.asalcedo.newsinshort.data.di

import com.asalcedo.newsinshort.data.AppConstants.APP_BASE_URL
import com.asalcedo.newsinshort.data.api.NewsApiService
import com.asalcedo.newsinshort.data.datasource.NewsRemoteDataSource
import com.asalcedo.newsinshort.data.datasource.NewsRemoteDataSourceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("BaseUrl")
    fun provideBaseUrl() = APP_BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit {

        val httpLoginInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val okHttpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoginInterceptor)
        }

        okHttpClient.apply {
            readTimeout(60, TimeUnit.SECONDS)
        }

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()


        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    /*
    We'll use to add token in header of request
    @Provides
    @Singleton
    fun provideOkhttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .addInterceptor(authInterceptor)
            .build()
    }

     */

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(apiService: NewsApiService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(apiService)
    }

}