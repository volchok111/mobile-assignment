package com.volchok.rocketapp.feature.home.presentation

import com.volchok.rocketapp.feature.home.domain.OpenRocketInfoUseCase
import com.volchok.rocketapp.library.api.domain.ObserveRocketsUseCase
import com.volchok.rocketapp.library.data.model.Data
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

//        advanceUntilIdle()
//        homeViewModel.states.value shouldBe
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}