package com.example.moviestreamingapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvSeries(
    val adult: Boolean,
    @SerialName(value = "backdrop_path")
    val backdropPath: String,
    @SerialName(value = "genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerialName(value = "origin_country")
    val originCountry: List<String>,
    @SerialName(value = "original_language")
    val originalLanguage: String,
    @SerialName(value = "original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerialName(value = "poster_path")
    val posterPath: String,
    @SerialName(value = "first_air_date")
    val firstAirDate: String,
    val name: String,
    @SerialName(value = "vote_average")
    val voteAverage: Double,
    @SerialName(value = "vote_count")
    val voteCount: Int
)
