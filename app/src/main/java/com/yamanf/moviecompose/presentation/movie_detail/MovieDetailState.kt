package com.yamanf.moviecompose.presentation.movie_detail

import com.yamanf.moviecompose.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading : Boolean = false,
    val movie : MovieDetail? = null,
    val error : String = ""
)
