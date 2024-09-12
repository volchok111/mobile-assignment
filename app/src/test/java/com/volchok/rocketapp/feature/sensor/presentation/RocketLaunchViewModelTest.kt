package com.volchok.rocketapp.feature.sensor.presentation

import com.volchok.rocketapp.feature.sensor.domain.ObserveRocketLaunchStageUseCase
import com.volchok.rocketapp.feature.sensor.model.RocketStages
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class RocketLaunchViewModelTest {
    private val observeRocketLaunchStageUseCase = mockk<ObserveRocketLaunchStageUseCase>()
    private fun createViewModel() = RocketLaunchViewModel(observeRocketLaunchStageUseCase)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should observe rocket stage and update state`() = runTest {
        coEvery { observeRocketLaunchStageUseCase.invoke(Unit) } returns flowOf(RocketStages.FlyingStage)

        val rocketLaunchViewModel = createViewModel()
        advanceUntilIdle()
        rocketLaunchViewModel.states.value.isStageFlying shouldBe true
    }

    @Test
    fun `should have isStageFlying false as a default state`() {
        coEvery { observeRocketLaunchStageUseCase.invoke(Unit) } returns emptyFlow()

        val rocketLaunchViewModel = createViewModel()
        rocketLaunchViewModel.states.value.isStageFlying shouldBe false
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}