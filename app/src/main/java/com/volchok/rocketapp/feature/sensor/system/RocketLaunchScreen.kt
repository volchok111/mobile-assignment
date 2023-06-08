package com.volchok.rocketapp.feature.sensor.system

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.volchok.rocketapp.R
import com.volchok.rocketapp.feature.sensor.presentation.RocketLaunchViewModel
import com.volchok.rocketapp.library.ui.RocketDimensions.sizeS
import org.koin.androidx.compose.getViewModel

@Composable
fun RocketLaunchScreen() {
    val viewModel = getViewModel<RocketLaunchViewModel>()
    val state = viewModel.states.collectAsState()

    RocketLaunchScreenImpl(
        state = state.value
    )
}

@Composable
private fun RocketLaunchScreenImpl(
    state: RocketLaunchViewModel.State
) {
    if (state.isReady) {
        AnimatedVisibility(
            visible = true,
            exit = slideOutVertically(targetOffsetY = { fullHeight -> fullHeight })
        ) {
            Image(
                painter = painterResource(R.drawable.rocket_flying),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
            )
        }
    }
}
