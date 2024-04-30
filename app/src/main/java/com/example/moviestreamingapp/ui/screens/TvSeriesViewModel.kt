package com.example.moviestreamingapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviestreamingapp.data.NetworkMoviesRepository
import com.example.moviestreamingapp.model.TvSeries
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface TvSeriesUiState {
    data class Success(val tvSeries: List<TvSeries>): TvSeriesUiState
    object Error: TvSeriesUiState
    object Loading: TvSeriesUiState
}
class TvSeriesViewModel : ViewModel(){

    var tvSeriesUiState: TvSeriesUiState by mutableStateOf(TvSeriesUiState.Loading)

    init {
        getTvSeries()
    }

    private fun getTvSeries(){
        viewModelScope.launch {
            try {
                val tvSeriesRepository = NetworkMoviesRepository()
                val response = tvSeriesRepository.getTvSeries()
                tvSeriesUiState = TvSeriesUiState.Success(response.results)
            } catch (e: IOException){
                TvSeriesUiState.Error
            }
        }
    }
}