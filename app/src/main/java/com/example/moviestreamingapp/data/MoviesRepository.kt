package com.example.moviestreamingapp.data

import com.example.moviestreamingapp.model.MovieList
import com.example.moviestreamingapp.model.TvList
import com.example.moviestreamingapp.network.MovieApi

interface MoviesRepository {
    suspend fun getMovies() : MovieList
    suspend fun getTvSeries(): TvList
}

class NetworkMoviesRepository: MoviesRepository {
    override suspend fun getMovies(): MovieList {
        return MovieApi.retrofitService.getMovies()
    }

    override suspend fun getTvSeries(): TvList {
        return MovieApi.retrofitService.getTvSeries()
    }
}