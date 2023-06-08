package com.volchok.rocketapp.app.system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.volchok.rocketapp.feature.sensor.system.SensorDelegate
import com.volchok.rocketapp.ui.theme.RocketAppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val sensorDelegate by inject<SensorDelegate>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorDelegate.onCreate(this)
        setContent {
            RocketAppTheme {
                MainScreen()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorDelegate.onDestroy()
    }
}