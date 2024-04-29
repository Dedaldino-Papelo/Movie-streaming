package com.example.moviestreamingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviestreamingapp.R
import com.example.moviestreamingapp.model.Movie
import com.example.moviestreamingapp.ui.screens.MovieUiState

@Composable
fun MovieScreen(
    movieUiState: MovieUiState,
    modifier: Modifier = Modifier)
{
    when(movieUiState){
        is MovieUiState.Loading -> LoadingScreen()
        is MovieUiState.Success -> MoviesGridScreen(
            movieUiState.movies,
            modifier = modifier.fillMaxWidth()
        )
        is MovieUiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen(){}

@Composable
fun ErrorScreen(){}

@Composable
fun MoviesGridScreen(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp),
        contentPadding = PaddingValues(start = 27.dp, end = 29.dp, bottom = 27.dp)
    ){
        items(items = movies, key = { movie -> movie.id }){ movie ->
            MoviesCard(
                movie,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun MoviesCard(
    movie: Movie,
    modifier: Modifier = Modifier){

    Column(
        modifier = modifier,
    ) {
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                    .crossfade(true)
                    .build(),
                contentDescription = "movie",
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = modifier.height(14.dp))

        Text(
            text = "${movie.originalTitle} (${movie.releaseDate.split("-")[0]})",
            color = colorResource(R.color.white),
            fontSize = 19.sp
        )
    }
}