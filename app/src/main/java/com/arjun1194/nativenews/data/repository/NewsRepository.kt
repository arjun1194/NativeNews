package com.arjun1194.nativenews.data.repository

import com.arjun1194.nativenews.api.NewsService
import com.arjun1194.nativenews.data.database.NewsDao
import com.arjun1194.nativenews.data.model.NewsResponse
import com.arjun1194.nativenews.utils.exception.DataNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val newsDao: NewsDao
) {

    suspend fun getTopHeadlines(): Flow<NewsResponse> {
        return flow {

            try {
                val networkResponse = newsService.getTopHeadlines()
                newsDao.insert(networkResponse.articles)
                // insert timestamp of insertion  in the database
            } catch (e: Exception) {
                emit(NewsResponse.Error(Throwable("Network is not available")))
            } finally {
                // if timestamp greater than 2 hours
                // delete
                val topHeadlines = newsDao.getTopHeadlines()
                if (topHeadlines.isEmpty())
                    emit(NewsResponse.Error(DataNotFoundException("Data is empty")))
                else emit(NewsResponse.Success(topHeadlines))
            }


        }.flowOn(Dispatchers.IO)
    }
}