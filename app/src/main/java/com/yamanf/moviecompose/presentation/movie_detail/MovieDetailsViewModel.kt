package com.yamanf.moviecompose.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yamanf.moviecompose.domain.use_case.get_movie_detail.GetMovieDetailUseCase
import com.yamanf.moviecompose.util.Constants.IMDB_ID
import com.yamanf.moviecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state : State<MovieDetailState> = _state

    init {
        stateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId : String){
        getMovieDetailUseCase.executeGetMovieDetails(imdbId).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value = MovieDetailState(movie = it.data)
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailState(true)
                }
                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message ?: "Error" )
                }
            }
        }.launchIn(viewModelScope)
    }

}