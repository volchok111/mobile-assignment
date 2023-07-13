package com.volchok.rocketapp.library.networking.domain

import com.volchok.rocketapp.library.networking.model.NetworkConnection
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ObserveConnectionUseCaseTest {

    private val networkController = mockk<NetworkController>()

    @Test
    fun `observing internet connection `() = runTest {

        val networkConnection = mockk<NetworkConnection>()

        every { networkController.observeNetworkChange() } returns flowOf(networkConnection)

        val observeConnectionUseCase = ObserveConnectionUseCase(networkController)

        val result = observeConnectionUseCase.invoke(Unit)

        result.first() shouldBe networkConnection
    }
}