package com.example.news_app.ui.view_models

import androidx.lifecycle.ViewModel
import com.example.news_app.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

}