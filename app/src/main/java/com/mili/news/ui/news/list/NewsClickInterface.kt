package com.mili.news.ui.news.list

import com.mili.news.data.entities.NewsArticle

interface NewsClickInterface {
    fun onNewsClick(news: NewsArticle)
}