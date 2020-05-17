package com.mili.news.data.network

import com.mili.news.comman.URLConstants.GET_DATA_FOR_CATEGORY
import com.mili.news.comman.URLConstants.GET_INIT_DATA
import com.mili.news.comman.URLConstants.GET_NEWS_AFTER_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {

    @GET("articles/id/{id}")
    suspend fun getInitNewsArticles(time: String): Response<NewsResponse>

    @GET("articles/id/{id}")
    suspend fun getNewsArticlesPostID(@Path("id") id: Int): Response<NewsResponse>
}