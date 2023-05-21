package com.example.news_app

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)