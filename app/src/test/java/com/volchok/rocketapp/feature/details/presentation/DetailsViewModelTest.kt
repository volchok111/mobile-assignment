package com.volchok.rocketapp.feature.details.presentation

import com.volchok.rocketapp.feature.details.domain.OpenRocketLaunchUseCase
import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.data.model.Data
import com.volchok.rocketapp.library.rockets.domain.FetchRocketInfoUseCase
import com.volchok.rocketapp.library.rockets.domain.ObserveRocketDetailsUseCase
import com.volchok.rocketapp.library.use_case.domain.invoke
import io.kotest.matchers.shouldBe
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class DetailsViewModelTest {

    private val fetchRocketInfoUseCase = mockk<FetchRocketInfoUseCase>()
    private val observeRocketDetailsUseCase = mockk<ObserveRocketDetailsUseCase>()
    private val openRocketLaunchUseCase = mockk<OpenRocketLaunchUseCase>()

    private val testRocketDetailsModel = mockk<RocketDetailsModel>()


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `if opens rocket launch screen`() = runTest {

        coEvery { fetchRocketInfoUseCase.invoke() } returns Data.Success(testRocketDetailsModel)

        coEvery { observeRocketDetailsUseCase.invoke() } returns flowOf(testRocketDetailsModel)

        every { openRocketLaunchUseCase.invoke(Unit) } just runs

        val detailsViewModel = DetailsViewModel(
            fetchRocketInfoUseCase,
            observeRocketDetailsUseCase,
            openRocketLaunchUseCase
        )

        detailsViewModel.onOpenRocketLaunch()

        verify { openRocketLaunchUseCase.invoke() }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `check loading state and rocket's details model state`() = runTest {

        coEvery { fetchRocketInfoUseCase.invoke() } returns Data.Success(testRocketDetailsModel)

        coEvery { observeRocketDetailsUseCase.invoke() } returns flowOf(testRocketDetailsModel)

        val detailsViewModel = DetailsViewModel(
            fetchRocketInfoUseCase,
            observeRocketDetailsUseCase,
            openRocketLaunchUseCase
        )

        advanceUntilIdle()

        detailsViewModel.states.value.rocket shouldBe testRocketDetailsModel

        detailsViewModel.states.value.loading shouldBe false
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}