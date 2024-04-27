package com.example.moviestreamingapp

sealed class Screen(val route: String){
    object Home: Screen("home")
}