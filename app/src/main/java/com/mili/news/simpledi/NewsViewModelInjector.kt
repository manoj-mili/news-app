package com.mili.news.simpledi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mili.news.comman.URLConstants.BASE_URL
import com.mili.news.data.NewsDatabase
import com.mili.news.data.dao.NewsArticleDao
import com.mili.news.data.network.NewsApi
import com.mili.news.data.repository.NewsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModelInjector(application: Application) : AndroidViewModel(application) {

    fun provideViewModel(): NewsViewModelFactory {
        return NewsViewModelFactory(provideNewsRepo())
    }

    private fun provideNewsRepo(): NewsRepository {
        return NewsRepository(provideRetroFitClient(), provideNewsDao(), getApplication())
    }

    private fun provideNewsDao(): NewsArticleDao {
        return NewsDatabase.defaultDB(getApplication()).newsArticleDao()
    }

    private fun provideRetroFitClient(): NewsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NewsApi::class.java)
    }

}