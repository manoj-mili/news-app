package com.mili.news.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mili.news.data.dao.NewsArticleDao
import com.mili.news.data.entities.NewsArticle

@Database(entities = [NewsArticle::class], version = 1)
abstract class NewsDatabase() : RoomDatabase() {

    abstract fun newsArticleDao(): NewsArticleDao
    companion object {
        fun defaultDB(context: Context) =
            Room.databaseBuilder(context, NewsDatabase::class.java, "news-db")
                .build()

    }
}