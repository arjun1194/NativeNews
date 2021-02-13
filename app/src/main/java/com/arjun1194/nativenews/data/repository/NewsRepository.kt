package com.arjun1194.nativenews.data.repository

import com.arjun1194.nativenews.api.NewsService
import com.arjun1194.nativenews.data.database.NewsDao
import com.arjun1194.nativenews.data.model.Article
import com.arjun1194.nativenews.data.model.DataResponse
import com.arjun1194.nativenews.data.model.Source
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

    suspend fun getTopHeadlines(): Flow<DataResponse<List<Article>>> {
        return flow {

            try {
                val networkResponse = newsService.getTopHeadlines()
                newsDao.deleteAll()
                newsDao.insert(networkResponse.articles)
                sharedPrefHelper.timestamp = Date().toString()
            } catch (e: Exception) {
                emit(DataResponse.Error(Throwable("Network is not available")))
            } finally {
                if (sharedPrefHelper.timestamp.isTimeGreaterThan())
                    newsDao.deleteAll()
                val topHeadlines = newsDao.getTopHeadlines()
                if (topHeadlines.isEmpty())
                    emit(DataResponse.Error(DataNotFoundException("Data is empty")))
                else emit(DataResponse.Success(topHeadlines))
            }


        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSources(): Flow<DataResponse<List<Source>>> {
        return flow{
            try {
                val networkResponse = newsService.getSources()
                emit(DataResponse.Success<List<Source>>(networkResponse.sources))
            } catch (e: Exception) {
                emit(DataResponse.Error(DataNotFoundException("Data is empty")))
            }
        }.flowOn(Dispatchers.IO)
    }
}