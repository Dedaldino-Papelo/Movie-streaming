package com.example.moviestreamingapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviestreamingapp.data.DataSource
import com.example.moviestreamingapp.ui.screens.HomeScreen

@Composable
fun MovieStreamingAppBar(){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieStreamingApp(){
    val navController = rememberNavController()

    Scaffold(

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(categories = DataSource.categories)
            }
        }
    }
}