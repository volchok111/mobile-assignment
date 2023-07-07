package com.volchok.rocketapp.library.api.domain

import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.data.model.Data
import com.volchok.rocketapp.library.use_case.domain.invoke
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ObserveRocketsUseCaseTest {

    private val rocketRepository = mockk<RemoteRepository>()

    @Test
    fun `should return rockets`() = runTest {

        val testRocketItems = mockk<List<RocketItem>>()

        coEvery { rocketRepository.getRockets() } returns Data.Success(testRocketItems)

        val observeRocketsUseCase = ObserveRocketsUseCase(rocketRepository)

        val result = observeRocketsUseCase.invoke()

        result.first() shouldBe Data.Success(testRocketItems)
    }
}