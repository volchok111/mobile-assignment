package com.volchok.rocketapp.feature.sensor.device

import com.volchok.rocketapp.feature.sensor.domain.SensorController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class AndroidSensorController : SensorController {

    private val launchResult = MutableSharedFlow<Boolean>()

    fun onLaunched(result: Boolean) {
        launchResult.tryEmit(result)
    }


    override fun launchRocket(): Flow<Boolean> = launchResult
}