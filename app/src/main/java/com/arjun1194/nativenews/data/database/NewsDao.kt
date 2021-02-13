package com.arjun1194.nativenews.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arjun1194.nativenews.data.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

   @Query("SELECT * FROM articles")
   suspend fun getTopHeadlines(): List<Article>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(articles: List<Article>): LongArray

   @Query("DELETE FROM articles")
   suspend fun deleteAll()

}