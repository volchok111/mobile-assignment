package com.volchok.rocketapp.app.domain

import com.volchok.rocketapp.app.model.NavigationEvent
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ObserveNavigationEventsUseCaseTest {

    private val mainNavigationController = mockk<MainNavigationController>()
    private val navigationEvent = mockk<NavigationEvent>()

    @Test
    fun `should navigate to the correct screen`() = runTest {
        every { mainNavigationController.navigationEvent } returns flowOf(navigationEvent)

        val observeNavigationEventsUseCase =
            ObserveNavigationEventsUseCase(mainNavigationController)
        val result = observeNavigationEventsUseCase.invoke(Unit)

        result.first() shouldBe navigationEvent
    }
}