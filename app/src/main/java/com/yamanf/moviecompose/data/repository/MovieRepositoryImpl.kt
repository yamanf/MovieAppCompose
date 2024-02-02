package com.yamanf.moviecompose.data.repository

import com.yamanf.moviecompose.data.remote.MovieAPI
import com.yamanf.moviecompose.data.remote.dto.MovieDetailDTO
import com.yamanf.moviecompose.data.remote.dto.MoviesDTO
import com.yamanf.moviecompose.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api : MovieAPI) : MovieRepository {
    override suspend fun getMovies(search: String): MoviesDTO {
        return api.getMovies(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDTO {
        return api.getMovie(imdbId)
    }


}