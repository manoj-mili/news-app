package com.mili.news.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mili.news.data.ViewState
import com.mili.news.data.entities.NewsArticle
import com.mili.news.data.repository.NewsRepository

class NewsSharedViewModel(private val repository: NewsRepository) : ViewModel() {

    private val newsData = MutableLiveData<NewsArticle>()
    private val fragChanged = MutableLiveData(false)

    fun getNewsArticles(category: String, status: Boolean): LiveData<ViewState<List<NewsArticle>>> {
        return repository.getArticlesFor(category, status).asLiveData()
    }

    fun showSelectedNews():LiveData<NewsArticle> = newsData

    fun fragChanged():LiveData<Boolean> = fragChanged

    fun changeSelectedArticle(news: NewsArticle) {
        newsData.value = news
    }

    fun updateFragmentChanged() {
        fragChanged.value = true
    }

}