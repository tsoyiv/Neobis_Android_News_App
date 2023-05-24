package com.example.news_app.repository

import androidx.room.Query
import com.example.news_app.api.RetrofitInstance
import com.example.news_app.database.ArticleDatabase
import com.example.news_app.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    //    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)
    suspend fun upsert(article: Article) = withContext(Dispatchers.IO) {
        db.getArticleDao().upsert(article)
    }

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}