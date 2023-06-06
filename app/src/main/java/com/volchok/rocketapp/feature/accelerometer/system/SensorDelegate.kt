package com.volchok.rocketapp.feature.accelerometer.system

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.ComponentActivity

class SensorDelegate: SensorEventListener {

    private lateinit var sensorManager: SensorManager

    fun onCreate(activity: ComponentActivity) {

    }



    override fun onSensorChanged(event: SensorEvent?) {
        TODO("Not yet implemented")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}