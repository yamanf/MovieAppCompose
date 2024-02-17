package com.yamanf.moviecompose.presentation.movies.views

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.yamanf.moviecompose.R
import com.yamanf.moviecompose.domain.model.Movie

@Composable
fun MovieListRow(
    movie: Movie,
    onItemClick : (Movie) -> Unit
) {
    Box(modifier = Modifier
        .padding(16.dp)
        .clip(RoundedCornerShape(16.dp)
    )) {
        Row(
            modifier = Modifier
                .background(Color.LightGray.copy(alpha = 0.3f))
                .fillMaxWidth()
                .clickable {
                    onItemClick(movie)
                }
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = movie.Poster),
                contentDescription = movie.Title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(6.dp)
                    .size(96.dp, 144.dp)
                    .clip(RoundedCornerShape(16.dp))

            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = movie.Title,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.opensans)),
                    fontWeight = FontWeight.Bold,
                    lineHeight = 38.sp
                )
                Text(
                    text = movie.Year,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.opensans)),
                    fontWeight = FontWeight.ExtraLight
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieListRowPreview() {
    MovieListRow(movie = Movie("https://tr.web.img4.acsta.net/pictures/18/01/18/15/21/5744571.jpg", "Batmand", "imdbId","1998"), onItemClick = { })
}