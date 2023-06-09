package com.volchok.rocketapp.feature.sensor.domain

import com.volchok.rocketapp.feature.sensor.model.RocketStages
import kotlinx.coroutines.flow.Flow

interface SensorController {

    fun launchRocket(): Flow<RocketStages>
}