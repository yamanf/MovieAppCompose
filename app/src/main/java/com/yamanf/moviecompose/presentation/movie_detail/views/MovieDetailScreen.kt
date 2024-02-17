package com.yamanf.moviecompose.presentation.movie_detail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.yamanf.moviecompose.R
import com.yamanf.moviecompose.presentation.movie_detail.MovieDetailsViewModel
import com.yamanf.moviecompose.presentation.movies.views.LoadingView

@Composable
fun MovieDetailScreen(
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val state = movieDetailsViewModel.state.value
    val gradientColors = listOf(
        Color(0xFFD04848).copy(alpha = 0f), // Köşeler
        Color(0xFFD04848).copy(alpha = 0.4f), // Orta
        Color(0xFFD04848).copy(alpha = 0.6f), // Orta
        Color(0xFFD04848).copy(alpha = 0.8f)  // Köşeler
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        state.movie?.let {
            Image(
                painter = rememberAsyncImagePainter(model = it.Poster),
                contentDescription = it.Title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(550.dp)
                    .clip(RectangleShape),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, // Tamamen saydam
                                Color.Black.copy(0.5f), // Tamamen saydam
                                Color.Black // Orta kısım opak siyah
                            ),
                            startY = 0f,
                            endY = 1400f // Gradyanın yüksekliği (istediğiniz değeri ayarlayın)
                        )
                    )
            )


            Column {
                Spacer(modifier = Modifier.height(350.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = gradientColors, startY = 0f, endY = Float.POSITIVE_INFINITY
                            )
                        )
                        .padding(top = 80.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                            .align(Alignment.BottomStart),
                    ) {
                        Text(
                            text = it.Title,
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.opensans)),
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            lineHeight = 30.sp
                        )
                        Row {
                            StarRatingBar(rating = it.imdbRating.toFloat() / 2)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${it.imdbRating.toFloat() / 2} / 5 ",
                                color = Color.LightGray,
                                fontFamily = FontFamily(Font(R.font.opensans)),
                                fontWeight = FontWeight.Light,
                                fontSize = 16.sp,
                            )
                        }
                        Row(modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = "${it.Released} - ",
                                color = Color.LightGray,
                                fontFamily = FontFamily(Font(R.font.opensans)),
                                fontWeight = FontWeight.Light,
                                fontSize = 14.sp,
                            )
                            Text(
                                text = it.Runtime,
                                color = Color.LightGray,
                                fontFamily = FontFamily(Font(R.font.opensans)),
                                fontWeight = FontWeight.Light,
                                fontSize = 14.sp,
                            )
                        }
                        Text(
                            text = it.Plot,
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color.LightGray,
                            fontFamily = FontFamily(Font(R.font.opensans)),
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Center)
            )
        }

        if (state.isLoading) {
            LoadingView(modifier = Modifier.fillMaxSize())
        }

    }
}