package com.yamanf.moviecompose.presentation.movies.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.yamanf.moviecompose.R

@Composable
fun NothingScreen(modifier: Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ghosts))
    Box(modifier = modifier) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = "There is nothing to see",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = 35.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp),
                textAlign = TextAlign.Center
            )
            LottieAnimation(
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally),
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
        }
    }
}

@Preview
@Composable
fun NothingScreenPreview() {
    NothingScreen(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp))
}