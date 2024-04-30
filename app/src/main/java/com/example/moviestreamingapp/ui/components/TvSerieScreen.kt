package com.example.moviestreamingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviestreamingapp.R
import com.example.moviestreamingapp.model.TvSeries
import com.example.moviestreamingapp.ui.screens.TvSeriesUiState

@Composable
fun TvSeriesScreen(
    tvSeriesUiState: TvSeriesUiState,
    modifier: Modifier = Modifier
){
    when(tvSeriesUiState){
        is TvSeriesUiState.Loading -> LoadingTvSeries()
        is TvSeriesUiState.Success -> TvSeriesGridScreen(
            tvSeriesUiState.tvSeries,
            modifier = modifier.fillMaxWidth()
        )
        is TvSeriesUiState.Error -> TvSeriesFailed()
    }
}

@Composable
fun LoadingTvSeries(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(60.dp),
            color = colorResource(R.color.category_selected_color),
            strokeWidth = 8.dp,
        )
    }
}

@Composable
fun TvSeriesFailed(){

}

@Composable
fun TvSeriesGridScreen(
    tvSeries: List<TvSeries>,
    modifier: Modifier = Modifier){

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp),
        contentPadding = PaddingValues(start = 27.dp, end = 29.dp, bottom = 27.dp)
    ){
        items(items = tvSeries, key = { tvSeries -> tvSeries.id }){ movie ->
            TvSeriesCard(
                movie,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun TvSeriesCard(
    tvSeries: TvSeries,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
    ) {
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500/${tvSeries.posterPath}")
                    .crossfade(true)
                    .build(),
                contentDescription = tvSeries.originalName,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = modifier.height(14.dp))

        Text(
            text = "${tvSeries.originalName}",
            color = colorResource(R.color.white),
            fontSize = 19.sp
        )
    }
}