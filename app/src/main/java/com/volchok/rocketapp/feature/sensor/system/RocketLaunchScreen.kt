package com.volchok.rocketapp.feature.sensor.system

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    LaunchedEffect(state.rocket) {

    }
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(sizeS)
    ) {
        Image(
            painter = rememberAsyncImagePainter(R.drawable.rocket_flying),
            contentDescription = null,
            //contentScale = ContentScale.Crop
        )
    }
}
