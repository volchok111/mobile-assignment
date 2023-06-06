package com.volchok.rocketapp.feature.accelerometer.device

import com.volchok.rocketapp.feature.accelerometer.domain.SensorController
import com.volchok.rocketapp.library.data.model.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class AndroidSensorController : SensorController {

    private val _requestedAccelerometerSensor = MutableSharedFlow<Unit>()
    val requestedAccelerometerSensor: Flow<Unit> = _requestedAccelerometerSensor


    override fun launchRocket(): Data<Unit> {
        TODO("Not yet implemented")
    }
}