package com.volchok.rocketapp.feature.accelerometer.domain

import com.volchok.rocketapp.library.data.model.Data

interface SensorController {

    fun launchRocket(): Data<Unit>
}