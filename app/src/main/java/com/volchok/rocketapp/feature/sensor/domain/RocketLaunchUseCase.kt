package com.volchok.rocketapp.feature.sensor.domain

import android.hardware.SensorEventListener
import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase

class RocketLaunchUseCase() : SynchronousUseCase<Unit, SensorEventListener> {
    override fun invoke(input: Unit): SensorEventListener {
        TODO("Not yet implemented")
    }
}