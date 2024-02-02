package com.yamanf.moviecompose.domain.repository

import com.yamanf.moviecompose.data.remote.dto.MovieDetailDTO
import com.yamanf.moviecompose.data.remote.dto.MoviesDTO

interface MovieRepository {


    suspend fun getMovies(search: String) : MoviesDTO

    suspend fun getMovieDetail(imdbId: String) : MovieDetailDTO

}