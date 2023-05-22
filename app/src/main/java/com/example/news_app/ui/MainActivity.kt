package com.example.news_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.news_app.R
import com.example.news_app.database.ArticleDatabase
import com.example.news_app.repository.NewsRepository
import com.example.news_app.ui.view_models.NewsViewModel
import com.example.news_app.ui.view_models.NewsViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var  viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
    }
}