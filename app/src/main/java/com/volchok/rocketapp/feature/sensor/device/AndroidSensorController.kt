package com.volchok.rocketapp.feature.sensor.device

import com.volchok.rocketapp.feature.sensor.model.RocketStages
import com.volchok.rocketapp.feature.sensor.domain.SensorController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class AndroidSensorController : SensorController {

    private val launchResult = MutableSharedFlow<RocketStages>(extraBufferCapacity = 1)

    fun onLaunched(result: RocketStages) {
        launchResult.tryEmit(result)
    }

    override fun launchRocket(): Flow<RocketStages> = launchResult
}