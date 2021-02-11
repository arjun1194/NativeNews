package com.arjun1194.nativenews.data.model

sealed class NewsResponse {
    data class Success(
        val topHeadlinesResponse: TopHeadlinesResponse
    ) : NewsResponse()

    data class Error(
        val message: String,
    ) : NewsResponse()

}


