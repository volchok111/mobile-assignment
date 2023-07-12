package com.volchok.rocketapp.feature.sensor.presentation

import com.volchok.rocketapp.feature.sensor.domain.ObserveRocketStageUseCase
import com.volchok.rocketapp.feature.sensor.model.RocketStages
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class RocketLaunchViewModelTest {

    private val observeRocketStageUseCase = mockk<ObserveRocketStageUseCase>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `check if launching stage is correct`() = runTest {

        coEvery { observeRocketStageUseCase.invoke(Unit) } returns flowOf(RocketStages.FlyingStage)

        val rocketLaunchViewModel = RocketLaunchViewModel(observeRocketStageUseCase)

        advanceUntilIdle()

        rocketLaunchViewModel.states.value.isStageFlying shouldBe true
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}