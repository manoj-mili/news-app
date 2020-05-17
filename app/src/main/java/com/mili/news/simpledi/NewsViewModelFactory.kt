package com.mili.news.simpledi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mili.news.data.repository.NewsRepository
import com.mili.news.ui.news.list.NewsSharedViewModel

class NewsViewModelFactory(private val newsRepo: NewsRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsSharedViewModel(newsRepo) as T
    }
}