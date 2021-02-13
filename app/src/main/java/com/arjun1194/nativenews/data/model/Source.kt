package com.arjun1194.nativenews.data.model

data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    var category: String,
    val language: String,
    val country: String
)
