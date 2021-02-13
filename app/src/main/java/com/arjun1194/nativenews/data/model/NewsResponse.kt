package com.arjun1194.nativenews.data.model

sealed class NewsResponse {
    data class Success(
        val articles: List<Article>
    ) : NewsResponse()

    data class Error(
        val throwable: Throwable,
    ) : NewsResponse()

}


