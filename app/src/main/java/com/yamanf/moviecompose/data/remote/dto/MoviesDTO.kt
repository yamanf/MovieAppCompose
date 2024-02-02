package com.yamanf.moviecompose.data.remote.dto

import com.yamanf.moviecompose.domain.model.Movie

data class MoviesDTO(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)

fun MoviesDTO.toMovieList() : List<Movie>{
    return Search.map { search ->  Movie(search.Poster,search.Title,search.imdbID,search.Year) }
}