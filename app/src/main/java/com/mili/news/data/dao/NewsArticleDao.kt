package com.mili.news.data.dao

import androidx.room.*
import com.mili.news.data.entities.NewsArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<NewsArticle>)

    @Query("SELECT * FROM news_article WHERE category=:selectCategory ORDER BY published DESC")
    fun getArticlesFor(selectCategory: String): Flow<List<NewsArticle>>

    @Delete
    suspend fun delete(list: List<NewsArticle>)

    @Query("DELETE FROM news_article WHERE datetime(published) <= datetime('now','-2 day') ")
    suspend fun delete()

    @Query("DELETE FROM news_article WHERE id <:idLimit")
    suspend fun delete(idLimit:Int)

    @Query("SELECT MAX(id) FROM news_article")
    fun getLastID(): Int

}