package com.volchok.rocketapp.app.presentation

import com.volchok.rocketapp.app.domain.ObserveNavigationEventsUseCase
import com.volchok.rocketapp.app.model.NavigationEvent
import com.volchok.rocketapp.library.networking.domain.ObserveConnectionUseCase
import com.volchok.rocketapp.library.networking.model.NetworkConnection
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class MainViewModelTest {

    private val observeNavigationEventsUseCase = mockk<ObserveNavigationEventsUseCase>()
    private val observeConnectionUseCase = mockk<ObserveConnectionUseCase>()
    private val networkConnection = mockk<NetworkConnection>()
    private val navigationEvent = mockk<NavigationEvent>()

    private fun createViewModel() =
        MainViewModel(observeNavigationEventsUseCase, observeConnectionUseCase)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `should navigate between screens`() {

        every { observeNavigationEventsUseCase.invoke(Unit) } returns flowOf(navigationEvent)

        every { observeConnectionUseCase.invoke(Unit) } returns flowOf(networkConnection)

        val mainViewModel = createViewModel()

        mainViewModel.onNavigationEventConsumed()

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should check network connection default state `() = runTest {

        every { observeNavigationEventsUseCase.invoke(Unit) } returns flowOf(navigationEvent)

        every { observeConnectionUseCase.invoke(Unit) } returns flowOf(networkConnection)

        val mainViewModel = createViewModel()

        advanceUntilIdle()

        mainViewModel.states.value.isOffline shouldBe false

        mainViewModel.states.value.navigationEvent shouldBe navigationEvent
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}