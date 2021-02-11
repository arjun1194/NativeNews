package com.arjun1194.nativenews.data.repository

import com.arjun1194.nativenews.api.NewsService
import com.arjun1194.nativenews.data.model.Article
import com.arjun1194.nativenews.data.model.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


class NewsRepository  @Inject constructor(private val newsService: NewsService){

    suspend fun getTopHeadlines(): Flow<NewsResponse> {
        return flow{
            try {
                val response = newsService.getTopHeadlines()
                //topHeadlinesDaoService.insert(response.body())
                emit(NewsResponse.Success(response))
            } catch (e:Exception){
                emit(NewsResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}