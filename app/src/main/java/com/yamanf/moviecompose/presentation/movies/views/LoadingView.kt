package com.yamanf.moviecompose.presentation.movies.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.yamanf.moviecompose.R

@Composable
fun LoadingView(modifier: Modifier) {
    val compositionLoading by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    Box(modifier = modifier) {
        LottieAnimation(
            iterations = LottieConstants.IterateForever,
            composition = compositionLoading,
        )
    }
}

@Preview
@Composable
fun LoadingViewPreview() {
    LoadingView(modifier = Modifier.fillMaxSize())
}