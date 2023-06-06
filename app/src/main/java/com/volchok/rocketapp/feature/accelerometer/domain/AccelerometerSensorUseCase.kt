package com.volchok.rocketapp.feature.accelerometer.domain

import android.hardware.SensorEventListener
import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase

class AccelerometerSensorUseCase() : SynchronousUseCase<Unit, SensorEventListener> {
    override fun invoke(input: Unit): SensorEventListener {
        TODO("Not yet implemented")
    }
}