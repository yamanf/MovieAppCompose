package com.yamanf.moviecompose.presentation.movies.views

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yamanf.moviecompose.R
import com.yamanf.moviecompose.presentation.Screen
import com.yamanf.moviecompose.presentation.movies.MoviesEvent
import com.yamanf.moviecompose.presentation.movies.MoviesViewModel

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var isFocused by remember { mutableStateOf(false) }
    var defaultSearchBarY = 240f
    var offsetSearchBarY by remember { mutableStateOf(defaultSearchBarY.dp) }
    val animatedOffsetXY by animateFloatAsState(
        targetValue = offsetSearchBarY.value,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )
    val animatedFontSize by animateFloatAsState(
        targetValue = if (isFocused.not()) 48f else 24f, label = ""
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "MovieCompose",
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = animatedOffsetXY.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.opensans)),
                color = Color.White,
                fontSize = animatedFontSize.sp,
                fontWeight = FontWeight.ExtraBold
            )
            if (isFocused.not()){
                Spacer(modifier = Modifier.height(16.dp))
            }
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .offset(y = animatedOffsetXY.dp),
                hint = "Search movie",
                onSearch = {
                    isFocused = true
                    viewModel.onEvent(MoviesEvent.Search(it))
                    offsetSearchBarY = 0.dp
                },
                onCancelled = {
                    if (it) {
                        offsetSearchBarY = defaultSearchBarY.dp
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


