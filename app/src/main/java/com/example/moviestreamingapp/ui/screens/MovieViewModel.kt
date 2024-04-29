package com.example.moviestreamingapp.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviestreamingapp.data.NetworkMoviesRepository
import com.example.moviestreamingapp.model.Movie
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MovieUiState {
    data class Success(val movies: List<Movie>): MovieUiState
    object Error: MovieUiState
    object Loading: MovieUiState
}
class MovieViewModel : ViewModel(){

    var movieUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)

    init {
        getMovies()
    }

    private fun getMovies(){
        viewModelScope.launch {
            try {
                val moviesRepository = NetworkMoviesRepository()
                var response = moviesRepository.getMovies()
                movieUiState = MovieUiState.Success(response.results)
                Log.d("response", response.results.size.toString())
            } catch (e: IOException){
                MovieUiState.Error
            }
        }
    }
}