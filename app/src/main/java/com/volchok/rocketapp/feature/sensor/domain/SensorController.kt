package com.volchok.rocketapp.feature.sensor.domain

import kotlinx.coroutines.flow.Flow

interface SensorController {

    fun launchRocket(): Flow<Boolean>
}