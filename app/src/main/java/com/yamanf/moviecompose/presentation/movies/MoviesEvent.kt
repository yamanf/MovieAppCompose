package com.yamanf.moviecompose.presentation.movies

sealed class MoviesEvent{
    data class Search(val searchString : String) : MoviesEvent()
}
