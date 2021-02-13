package com.arjun1194.nativenews.di

import com.arjun1194.nativenews.api.NewsService
import com.arjun1194.nativenews.data.database.NewsDao
import com.arjun1194.nativenews.data.repository.NewsRepository
import com.arjun1194.nativenews.utils.SharedPrefHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideNewsRepository(
        newsService: NewsService,
        newsDao: NewsDao,
        sharedPrefHelper: SharedPrefHelper
    ): NewsRepository {
        return NewsRepository(newsService, newsDao,sharedPrefHelper)
    }
}