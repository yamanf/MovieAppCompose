package com.yamanf.moviecompose.presentation.movies.views

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.yamanf.moviecompose.R
import com.yamanf.moviecompose.presentation.Screen
import com.yamanf.moviecompose.presentation.movies.MoviesEvent
import com.yamanf.moviecompose.presentation.movies.MoviesViewModel
import com.yamanf.moviecompose.util.Resource

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    var isFocused by remember { mutableStateOf(false) }

    var offsetY by remember { mutableStateOf(300.dp) }

    val animatedOffsetXY by animateFloatAsState(
        targetValue = offsetY.value,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .offset(y = animatedOffsetXY.dp),
                hint = "Search movie",
                onSearch = {
                    isFocused = true
                    viewModel.onEvent(MoviesEvent.Search(it))
                    offsetY = 0.dp
                },
                onCancelled = {
                    if (it) {
                        offsetY = 300.dp
                        isFocused = false
                    }
                }
            )
            if (isFocused && state.isLoading.not()) {
                if (state.movies.isNotEmpty()) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.movies) { movie ->
                            MovieListRow(movie = movie, onItemClick = {
                                navController.navigate(Screen.MovieDetailScreen.route + "/${movie.imdbID}")
                            })
                        }
                    }
                } else if (state.movies.isEmpty()) {
                    NothingScreen(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp))
                }
            }else if (isFocused && state.isLoading){
                LoadingView(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


