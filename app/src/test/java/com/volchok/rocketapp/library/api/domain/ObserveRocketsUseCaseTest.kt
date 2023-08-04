package com.volchok.rocketapp.library.api.domain

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
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
    private val testRocketItems = mockk<List<RocketItem>>()
    private val favoriteRocketRepository = mockk<FavoriteRocketRepository>()

    @Test
    fun `should return rockets`() = runTest {
        coEvery { rocketRepository.getRockets() } returns Data.Success(testRocketItems)

        val observeRocketsUseCase =
            ObserveRocketsUseCase(rocketRepository, favoriteRocketRepository)

        val result = observeRocketsUseCase.invoke()
        result.first() shouldBe Data.Success(testRocketItems)
    }
}