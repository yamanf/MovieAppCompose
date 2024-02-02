package com.yamanf.moviecompose.presentation.movies

import com.yamanf.moviecompose.domain.model.Movie
import java.lang.Error

data class MoviesState(
    val isLoading : Boolean = false,
    val movies : List<Movie> = emptyList(),
    val error: String = "",
    val search : String = ""
)
