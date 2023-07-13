package com.volchok.rocketapp.feature.sensor.domain

import com.volchok.rocketapp.feature.sensor.model.RocketStages
import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

class ObserveRocketStageUseCase(
    private val sensorController: SensorController
) : SynchronousUseCase<Unit, Flow<RocketStages>> {
    override fun invoke(input: Unit): Flow<RocketStages> = sensorController.observeRocketLaunchStage()
}