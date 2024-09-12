package com.volchok.rocketapp.feature.sensor.system

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.ComponentActivity
import com.volchok.rocketapp.feature.sensor.device.AndroidSensorController
import com.volchok.rocketapp.feature.sensor.model.RocketStages

class SensorDelegate(
    private val sensorController: AndroidSensorController
) {
    private lateinit var activity: ComponentActivity

    fun onCreate(activity: ComponentActivity) {
        this.activity = activity
        setUpSensor()
    }

    fun onDestroy() {
        getSensorManager().unregisterListener(sensorEventListener)
    }

    private val sensorEventListener = object : SensorEventListener {

        override fun onSensorChanged(event: SensorEvent?) {
            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                val upDown = event.values[1]
                if (upDown.toDouble() > VALUE_TO_LAUNCH_ROCKET && upDown.toDouble() < VALUE_TO_LAUNCH_ROCKET + DEVIATION) {
                    sensorController.onLaunched(RocketStages.FlyingStage)
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            return
        }
    }

    private fun setUpSensor() {
        getSensorManager().getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
            getSensorManager().registerListener(
                sensorEventListener,
                it,
                SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    private fun getSensorManager(): SensorManager {
        return activity.applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    companion object {
        private const val VALUE_TO_LAUNCH_ROCKET = 6.93// Accelerometer value in m/s2
        private const val DEVIATION = 0.06 // Accelerometer value in m/s2
    }
}