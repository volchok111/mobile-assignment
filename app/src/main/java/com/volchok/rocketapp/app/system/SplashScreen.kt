package com.volchok.rocketapp.app.system

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.volchok.rocketapp.app.presentation.SplashViewModel
import org.koin.androidx.compose.getViewModel


//TODO add splash screen implementation
@Composable
fun SplashScreen() {
    getViewModel<SplashViewModel>()

    SplashScreenImpl()
}

@Composable
fun SplashScreenImpl() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Splash Screen")
    }
}