package com.volchok.rocketapp.feature.home.presentation

import com.volchok.rocketapp.feature.home.domain.OpenRocketInfoUseCase
import com.volchok.rocketapp.library.api.domain.ObserveRocketsUseCase
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.data.model.Data
import io.kotest.matchers.shouldBe
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class HomeViewModelTest {

    private val rocketId = "falcon_heavy"
    private val observeRocketsUseCase = mockk<ObserveRocketsUseCase>()
    private val openRocketInfoUseCase = mockk<OpenRocketInfoUseCase>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `if clicked on correct item`() = runTest {

        coEvery { observeRocketsUseCase.invoke(Unit) } returns flowOf(Data.Success(emptyList()))

        every { openRocketInfoUseCase.invoke(any()) } just runs

        val homeViewModel = HomeViewModel(observeRocketsUseCase, openRocketInfoUseCase)

        homeViewModel.onItem(rocketId)

        verify { openRocketInfoUseCase.invoke(rocketId) }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return rockets`() = runTest {

        val testRocketModel =
            RocketItem(first_flight = "1.1.1111", rocket_name = "Falcon", rocket_id = rocketId)

        val testViewModelRocketItem = HomeViewModel.State.RocketItem(
            first_flight = "1.1.1111",
            rocket_name = "Falcon",
            rocket_id = rocketId
        )

        coEvery { observeRocketsUseCase.invoke(Unit) } returns flowOf(
            Data.Success(listOf(testRocketModel))
        )

        val homeViewModel = HomeViewModel(observeRocketsUseCase, openRocketInfoUseCase)

        advanceUntilIdle()

        homeViewModel.states.value.rockets shouldBe listOf(testViewModelRocketItem)

        homeViewModel.states.value.loading shouldBe false
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}