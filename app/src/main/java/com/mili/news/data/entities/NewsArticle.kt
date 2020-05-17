package com.mili.news.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_article")
data class NewsArticle(


    /**
     * Primary key for Room.
     */
    @PrimaryKey
    val id: Int = 0,

    /**
     * Name of the author for the article
     */
    val author: String,

    /**
     * Title of the article
     */
    val title: String,

    /**
     * Complete description of the article
     */
    val description: String,

    /**
     * URL to the article
     */
    val url: String,

    /**
     * URL of the image shown with article
     */
    val image: String,

    /**
     * Date-time when the article was published
     */
    val published: String,

    /**
     * Category of the article ex - tech, business etc
     */
    val category: String,

    /**
     * Source of the article ex - new york times, business today etc
     */
    val source: String,

    /**
     * Small content of the article
     */
    val content: String


)