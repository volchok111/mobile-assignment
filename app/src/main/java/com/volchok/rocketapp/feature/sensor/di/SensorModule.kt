package com.volchok.rocketapp.feature.sensor.di

import com.volchok.rocketapp.feature.sensor.device.AndroidSensorController
import com.volchok.rocketapp.feature.details.domain.OpenRocketLaunchUseCase
import com.volchok.rocketapp.feature.sensor.domain.RocketLaunchUseCase
import com.volchok.rocketapp.feature.sensor.domain.SensorController
import com.volchok.rocketapp.feature.sensor.presentation.RocketLaunchViewModel
import com.volchok.rocketapp.feature.sensor.system.SensorDelegate
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sensorModule = module {
    viewModelOf(::RocketLaunchViewModel)
    singleOf(::AndroidSensorController) bind SensorController::class
    factoryOf(::SensorDelegate)
    factoryOf(::RocketLaunchUseCase)
}