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

    @Test
    fun `check if navigate to the correct screen`() = runTest {
        val navigationEvent = mockk<NavigationEvent>()

        every { mainNavigationController.navigationEvent } returns flowOf(navigationEvent)

        val observeNavigationEventsUseCase =
            ObserveNavigationEventsUseCase(mainNavigationController)

        val result = observeNavigationEventsUseCase.invoke(Unit)

        result.first() shouldBe navigationEvent
    }
}