package com.volchok.rocketapp.library.rockets.domain

import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ObserveRocketDetailsUseCaseTest {

    private val localRocketRepository = mockk<LocalRocketRepository>()

    @Test
    fun `should return rockets details`() = runTest {
        val testRocketDetailsModel = mockk<RocketDetailsModel>()

        every { localRocketRepository.rocket } returns flowOf(testRocketDetailsModel)

        val observeRocketDetailsUseCase = ObserveRocketDetailsUseCase(localRocketRepository)

        val result = observeRocketDetailsUseCase.invoke(Unit)

        result.first() shouldBe testRocketDetailsModel
    }
}