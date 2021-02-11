package com.arjun1194.nativenews.data.model

data class TopHeadlinesResponse(
    val status: String,
    val totalResults:Int,
    val articles: List<Article>
)