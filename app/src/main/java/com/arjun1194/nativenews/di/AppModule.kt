package com.arjun1194.nativenews.di

import android.content.Context
import androidx.room.PrimaryKey
import com.arjun1194.nativenews.R
import com.arjun1194.nativenews.api.NewsService
import com.arjun1194.nativenews.utils.SharedPrefHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


    @Singleton
    @Provides
    fun providesSharedHelper(
        @ApplicationContext context: Context
    ): SharedPrefHelper {
        val sharedPreferences = context.getSharedPreferences(
            context.getString(R.string.shared_pref_file),
            Context.MODE_PRIVATE
        )
        return SharedPrefHelper(sharedPreferences)
    }
}