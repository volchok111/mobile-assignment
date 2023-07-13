package com.volchok.rocketapp.feature.sensor.domain

import com.volchok.rocketapp.feature.sensor.model.RocketStages
import com.volchok.rocketapp.library.use_case.domain.invoke
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ObserveRocketStageUseCaseTest {

    private val sensorController = mockk<SensorController>()

    @Test
    fun `should return rocket stage`() = runTest {

        val rocketStages = mockk<RocketStages>()

        coEvery { sensorController.observeRocketLaunchStage() } returns flowOf(rocketStages)

        val observeRocketStagesUseCase = ObserveRocketStageUseCase(sensorController)

        val result = observeRocketStagesUseCase.invoke()

        result.first() shouldBe rocketStages
    }
}