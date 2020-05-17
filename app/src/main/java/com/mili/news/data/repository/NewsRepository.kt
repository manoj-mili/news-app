package com.mili.news.data.repository

import android.content.Context
import com.mili.news.comman.GlobalConstants
import com.mili.news.comman.GlobalConstants.LAST_SYNC_TIME
import com.mili.news.comman.readFromSharedPref
import com.mili.news.comman.writeToSharedPref
import com.mili.news.data.NetworkBoundResource
import com.mili.news.data.ViewState
import com.mili.news.data.dao.NewsArticleDao
import com.mili.news.data.entities.NewsArticle
import com.mili.news.data.network.NewsApi
import com.mili.news.data.network.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class NewsRepository(
    private val newsApi: NewsApi,
    private val newsDao: NewsArticleDao,
    private val context: Context
) {

    fun getArticlesFor(category: String, status: Boolean): Flow<ViewState<List<NewsArticle>>> {
        return object : NetworkBoundResource<List<NewsArticle>, NewsResponse>() {
            override suspend fun saveNetworkResult(response: NewsResponse) {
                writeToSharedPref(context, LAST_SYNC_TIME, System.currentTimeMillis().toString())
                newsDao.delete()
                return newsDao.insert(response.articles)
            }

            // Fetch based on certain criteria
            override fun shouldFetch(data: List<NewsArticle>?): Boolean {
                return status && (data == null || data.isEmpty() || System.currentTimeMillis() - readFromSharedPref(
                    context,
                    GlobalConstants.LAST_SYNC_TIME, "0"
                ).toLong() > 1000)
            }

            override fun loadFromDb(): Flow<List<NewsArticle>> = newsDao.getArticlesFor(category)
            override suspend fun fetchFromNetwork(dataExist: Boolean): Response<NewsResponse> =
                newsApi.getNewsArticlesPostID(newsDao.getLastID())


        }.asFlow().flowOn(Dispatchers.IO)
    }
}