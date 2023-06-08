package com.volchok.rocketapp.feature.sensor.system

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.ComponentActivity
import androidx.core.graphics.rotationMatrix
import com.volchok.rocketapp.feature.sensor.device.AndroidSensorController
import com.volchok.rocketapp.feature.sensor.presentation.RocketLaunchViewModel

class SensorDelegate(
    private val sensorController: AndroidSensorController
) {
    private lateinit var activity: ComponentActivity
    private lateinit var state: RocketLaunchViewModel.State

    private val sensorEventListener = object : SensorEventListener {

        override fun onSensorChanged(event: SensorEvent?) {
            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                val upDown = event.values[1]


//                state.rocket.apply {
//                    rotationMatrix(upDown * 3f)
//                }
                sensorController.onLaunched(true)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            return
        }

    }

    fun onCreate(activity: ComponentActivity) {
        this.activity = activity
        setUpSensor()
    }

    fun onDestroy() {
        getSensorManager().unregisterListener(sensorEventListener)
    }

    private fun setUpSensor() {
        getSensorManager().getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
            getSensorManager().registerListener(
                sensorEventListener,
                it,
                SensorManager.SENSOR_DELAY_FASTEST,
               // SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    private fun getSensorManager(): SensorManager {
        return activity.applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
}