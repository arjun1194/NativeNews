package com.arjun1194.nativenews.api

import com.arjun1194.nativenews.data.model.SourcesResponse
import com.arjun1194.nativenews.data.model.TopHeadlinesResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {


    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "in",
    ): TopHeadlinesResponse

    @GET("sources")
    suspend fun getSources(
        @Query("country") country: String = "in",
    ): SourcesResponse


    companion object {
        fun create(): NewsService {
            val logger = HttpLoggingInterceptor().apply { level = BASIC }

            val authInterceptor = Interceptor {
                val newRequest = it.request().newBuilder()
                    .addHeader("x-api-key", "416399bd74834bf0b8cc23cbc9985360")
                    .build()
                it.proceed(newRequest)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(authInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsService::class.java)
        }
    }

}