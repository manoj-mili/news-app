package com.mili.news.comman

object URLConstants {
    const val BASE_URL = "https://mili-dev.ml/"
    const val GET_NEWS_AFTER_ID = BASE_URL + "articles/id/{id}"
    const val GET_INIT_DATA = BASE_URL + "articles/time/{time}"
    const val GET_DATA_FOR_CATEGORY = BASE_URL + "articles/category/{category}"
}