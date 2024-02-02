package com.yamanf.moviecompose.data.remote

import com.yamanf.moviecompose.data.remote.dto.MovieDetailDTO
import com.yamanf.moviecompose.data.remote.dto.MoviesDTO
import com.yamanf.moviecompose.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey : String = API_KEY
    ) : MoviesDTO


    @GET(".")
    suspend fun getMovie(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey : String = API_KEY
    ) : MovieDetailDTO

}