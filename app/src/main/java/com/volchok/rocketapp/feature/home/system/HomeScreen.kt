package com.volchok.rocketapp.feature.home.system

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.volchok.rocketapp.feature.home.presentation.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen() {
    val viewModel = getViewModel<HomeViewModel>()
    val state = viewModel.states.collectAsState()

    HomeScreenImpl()
}

@Composable
fun HomeScreenImpl() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        //.background(background)
        contentAlignment = Alignment.Center,

        ) {
        Text(text = "Home Screen")
    }
}