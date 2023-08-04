package com.volchok.rocketapp.feature.details.presentation

import com.volchok.rocketapp.feature.details.domain.OpenRocketLaunchUseCase
import com.volchok.rocketapp.feature.details.domain.UpdateFavoriteRocketByIdUseCase
import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.data.model.Data
import com.volchok.rocketapp.library.rockets.domain.FetchRocketInfoUseCase
import com.volchok.rocketapp.library.rockets.domain.ObserveRocketDetailsUseCase
import com.volchok.rocketapp.library.use_case.domain.invoke
import io.kotest.matchers.shouldBe
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
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
    private val updateFavoriteRocketByIdUseCase = mockk<UpdateFavoriteRocketByIdUseCase>()
    private val rocketId = "falcon_heavy"

    private fun createViewModel() = DetailsViewModel(
        fetchRocketInfoUseCase,
        observeRocketDetailsUseCase,
        openRocketLaunchUseCase,
        updateFavoriteRocketByIdUseCase
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `should open rocket launch screen`() = runTest {
        coEvery { fetchRocketInfoUseCase() } returns Data.Success(testRocketDetailsModel)
        coEvery { observeRocketDetailsUseCase() } returns flowOf(testRocketDetailsModel)
        every { openRocketLaunchUseCase.invoke(Unit) } just runs

        val detailsViewModel = createViewModel()
        detailsViewModel.onOpenRocketLaunch()

        verify { openRocketLaunchUseCase.invoke() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should fetch and observe rockets and set loading state and rocket state`() = runTest {
        coEvery { fetchRocketInfoUseCase.invoke() } returns Data.Success(testRocketDetailsModel)
        coEvery { observeRocketDetailsUseCase.invoke() } returns flowOf(testRocketDetailsModel)

        val detailsViewModel = createViewModel()
        advanceUntilIdle()

        detailsViewModel.states.value.rocket shouldBe testRocketDetailsModel
        detailsViewModel.states.value.loading shouldBe false
        coVerify { fetchRocketInfoUseCase.invoke() }
    }

    @Test
    fun `should have loading true and rocket null as a default state`() {
        coEvery { observeRocketDetailsUseCase.invoke() } returns emptyFlow()

        val detailsViewModel = createViewModel()
        detailsViewModel.states.value.loading shouldBe true
        detailsViewModel.states.value.rocket shouldBe null
    }

//    @Test
//    fun `should update favorite rocket by id`() = runTest {
//        coEvery { updateFavoriteRocketByIdUseCase.invoke(any()) } just runs
//        coEvery { fetchRocketInfoUseCase.invoke() } returns Data.Success(testRocketDetailsModel)
//        coEvery { observeRocketDetailsUseCase.invoke() } returns flowOf(testRocketDetailsModel)
//
//        val detailsViewModel = createViewModel()
//        detailsViewModel.onLikeClicked()
//
//        coVerify { updateFavoriteRocketByIdUseCase.invoke(rocketId) }
//    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}