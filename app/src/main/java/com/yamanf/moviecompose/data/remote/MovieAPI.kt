package com.yamanf.moviecompose.data.remote

import com.yamanf.moviecompose.BuildConfig
import com.yamanf.moviecompose.data.remote.dto.MovieDetailDTO
import com.yamanf.moviecompose.data.remote.dto.MoviesDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey : String = BuildConfig.API_KEY
    ) : MoviesDTO


    @GET(".")
    suspend fun getMovie(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey : String = BuildConfig.API_KEY
    ) : MovieDetailDTO

}