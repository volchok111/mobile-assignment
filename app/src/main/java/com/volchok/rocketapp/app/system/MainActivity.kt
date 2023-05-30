package com.volchok.rocketapp.app.system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.volchok.rocketapp.ui.theme.RocketAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketAppTheme {
                MainScreen()
            }
        }
    }
}