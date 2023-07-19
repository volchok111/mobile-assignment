package com.volchok.rocketapp.app.presentation

import com.volchok.rocketapp.app.domain.ObserveNavigationEventsUseCase
import com.volchok.rocketapp.app.model.NavigationEvent
import com.volchok.rocketapp.library.networking.domain.ObserveConnectionUseCase
import com.volchok.rocketapp.library.networking.model.NetworkConnection
import com.volchok.rocketapp.library.use_case.domain.invoke
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should navigate between screens`() = runTest {
        every { observeNavigationEventsUseCase.invoke(Unit) } returns flowOf(navigationEvent)
        every { observeConnectionUseCase.invoke(Unit) } returns flowOf(networkConnection)

        advanceUntilIdle()
        val mainViewModel = createViewModel()
        mainViewModel.onNavigationEventConsumed()
        mainViewModel.states.value.navigationEvent shouldBe null

        verify { observeNavigationEventsUseCase.invoke() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should have isOffline false as a default connection state`() = runTest {
        every { observeNavigationEventsUseCase.invoke(Unit) } returns flowOf(navigationEvent)
        every { observeConnectionUseCase.invoke(Unit) } returns emptyFlow()

        val mainViewModel = createViewModel()
        advanceUntilIdle()
        mainViewModel.states.value.isOffline shouldBe false
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should have initial route as a default navigation event`() = runTest {
        every { observeNavigationEventsUseCase.invoke(Unit) } returns flowOf(navigationEvent)
        every { observeConnectionUseCase.invoke(Unit) } returns emptyFlow()

        val mainViewModel = createViewModel()
        advanceUntilIdle()
        mainViewModel.states.value.navigationEvent shouldBe navigationEvent
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should set is offline based on the networkConnection state`() = runTest {
        every { observeNavigationEventsUseCase.invoke(Unit) } returns flowOf(navigationEvent)
        every { observeConnectionUseCase.invoke(Unit) } returns flowOf(NetworkConnection.Offline)

        val mainViewModel = createViewModel()
        advanceUntilIdle()
        mainViewModel.states.value.isOffline shouldBe true

        verify { observeConnectionUseCase.invoke() }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}