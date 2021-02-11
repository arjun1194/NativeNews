package com.arjun1194.nativenews.di

import com.arjun1194.nativenews.api.NewsService
import com.arjun1194.nativenews.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {



    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return NewsService.create()
    }
}