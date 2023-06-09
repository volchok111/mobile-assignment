package com.volchok.rocketapp.feature.sensor.system

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.volchok.rocketapp.R
import com.volchok.rocketapp.feature.sensor.presentation.RocketLaunchViewModel
import com.volchok.rocketapp.library.ui.RocketDimensions.sizeL
import com.volchok.rocketapp.library.ui.RocketDimensions.sizeXXL
import com.volchok.rocketapp.library.ui.RocketText
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
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.success1))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = state.isStageFlying,
        iterations = 1
    )
    if (state.isStageFlying) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            alignment = Alignment.TopCenter
        )
    }
    AnimatedVisibility(
        visible = state.isStageFlying,
        enter = slideInVertically(
            initialOffsetY = { 1500 },
            animationSpec = tween(durationMillis = 6000)
        ),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.rocket_flying),
                contentDescription = null,
            )
        }
    }
    if (!state.isStageFlying) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(sizeXXL))
            Image(
                painter = painterResource(R.drawable.rocket_start),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(sizeL))
            RocketText(
                text = stringResource(id = R.string.launch_screen_move_phone),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = sizeXXL)
            )
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {
            RocketText(
                text = stringResource(id = R.string.launch_screen_launch_success),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = sizeXXL)
            )
        }
    }
}

