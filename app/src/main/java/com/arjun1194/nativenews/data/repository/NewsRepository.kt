package com.arjun1194.nativenews.data.repository

import com.arjun1194.nativenews.api.NewsService
import com.arjun1194.nativenews.data.database.NewsDao
import com.arjun1194.nativenews.data.model.NewsResponse
import com.arjun1194.nativenews.utils.SharedPrefHelper
import com.arjun1194.nativenews.utils.exception.DataNotFoundException
import com.arjun1194.nativenews.utils.isTimeGreaterThan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val newsDao: NewsDao,
    private val sharedPrefHelper: SharedPrefHelper
) {

    suspend fun getTopHeadlines(): Flow<NewsResponse> {
        return flow {

            try {
                val networkResponse = newsService.getTopHeadlines()
                newsDao.deleteAll()
                newsDao.insert(networkResponse.articles)
                sharedPrefHelper.timestamp = Date().toString()
            } catch (e: Exception) {
                emit(NewsResponse.Error(Throwable("Network is not available")))
            } finally {
                if (sharedPrefHelper.timestamp.isTimeGreaterThan())
                    newsDao.deleteAll()
                val topHeadlines = newsDao.getTopHeadlines()
                if (topHeadlines.isEmpty())
                    emit(NewsResponse.Error(DataNotFoundException("Data is empty")))
                else emit(NewsResponse.Success(topHeadlines))
            }


        }.flowOn(Dispatchers.IO)
    }
}