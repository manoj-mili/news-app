package com.mili.news.data.network

import com.google.gson.annotations.SerializedName
import com.mili.news.data.entities.NewsArticle

data class NewsResponse(
    val status: Boolean = false,

    val message: String = "",

    @SerializedName("data")
    val articles: List<NewsArticle> = emptyList()
)