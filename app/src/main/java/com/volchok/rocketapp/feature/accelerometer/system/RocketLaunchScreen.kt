package com.volchok.rocketapp.feature.accelerometer.system

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.volchok.rocketapp.R
import com.volchok.rocketapp.feature.accelerometer.presentation.RocketLaunchViewModel
import com.volchok.rocketapp.library.ui.RocketDimensions.sizeS
import org.koin.androidx.compose.getViewModel

@Composable
fun RocketLaunchScreen() {
    val viewModel = getViewModel<RocketLaunchViewModel>()
    val state = viewModel.states.collectAsState()

    RocketLaunchScreenImpl()
}

@Composable
private fun RocketLaunchScreenImpl() {
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

@Composable
private fun AccelerometerSensor(
    state: RocketLaunchViewModel.State
) {
    val context = LocalContext.current
    val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelSensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                val upDown = event.values[1]

                state.rocket.apply {

                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            return
        }

    }
}

@Preview
@Composable
private fun RocketLaunchPreview() {
    RocketLaunchScreenImpl()
}
