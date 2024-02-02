package com.yamanf.moviecompose.domain.use_case.get_movie_detail

import com.yamanf.moviecompose.data.remote.dto.toMovieDetail
import com.yamanf.moviecompose.data.remote.dto.toMovieList
import com.yamanf.moviecompose.domain.model.MovieDetail
import com.yamanf.moviecompose.domain.repository.MovieRepository
import com.yamanf.moviecompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {

    fun executeGetMovieDetails(imdbId : String) : Flow<Resource<MovieDetail>> = flow  {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId)
            emit(Resource.Success(movieDetail.toMovieDetail()))
        }catch (e : Exception){
            emit(Resource.Error("Connection problem"))
        }
    }

}