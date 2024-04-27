package com.example.moviestreamingapp.data

import com.example.moviestreamingapp.model.Category

object DataSource {
    val categories = listOf<Category>(
        Category("1", "Movies"),
        Category("2", "TV Series"),
        Category("3", "Documentary"),
        Category("4", "Sports"),
    )
}