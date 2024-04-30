package com.example.moviestreamingapp.network

import com.example.moviestreamingapp.BuildConfig
import com.example.moviestreamingapp.model.MovieList
import com.example.moviestreamingapp.model.TvList
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = BuildConfig.API_KEY

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory(("application/json".toMediaType())))
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService {
    @GET("movie/popular?api_key=${API_KEY}")
    suspend fun getMovies(): MovieList

    @GET("tv/popular?api_key=${API_KEY}")
    suspend fun getTvSeries(): TvList
}

object MovieApi {
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}