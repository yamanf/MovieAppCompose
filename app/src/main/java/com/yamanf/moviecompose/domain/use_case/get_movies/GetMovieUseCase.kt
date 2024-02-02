package com.yamanf.moviecompose.domain.use_case.get_movies

import com.yamanf.moviecompose.data.remote.dto.toMovieList
import com.yamanf.moviecompose.domain.model.Movie
import com.yamanf.moviecompose.domain.repository.MovieRepository
import com.yamanf.moviecompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    fun executeGetMovies(search : String) : Flow<Resource<List<Movie>>> = flow  {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)
            if (movieList.Response.equals("True")){
                emit(Resource.Success(movieList.toMovieList()))
            } else {
                emit(Resource.Error("No movie found!"))
            }
        }catch (e : Exception){
            emit(Resource.Error(e.localizedMessage ?: "Connection problem"))
        }
    }
}