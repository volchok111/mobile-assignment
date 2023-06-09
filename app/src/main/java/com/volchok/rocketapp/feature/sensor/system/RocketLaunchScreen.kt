package com.volchok.rocketapp.feature.sensor.system

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.volchok.rocketapp.R
import com.volchok.rocketapp.feature.sensor.presentation.RocketLaunchViewModel
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
    AnimatedVisibility(
        visible = state.isStageFlying,
        enter = slideInVertically(
            initialOffsetY = { it/2 },
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
            Image(
                painter = painterResource(R.drawable.rocket_start),
                contentDescription = null,
            )
        }
    }
}

