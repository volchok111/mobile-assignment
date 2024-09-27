package com.volchok.rocketapp.feature.details.domain

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class UpdateFavoriteRocketByIdUseCaseTest {
    private val favoriteRocketRepository = mockk<FavoriteRocketRepository>()
    private val rocketId = "falcon_heavy"

    @Test
    fun `should update favorite rocket if it's selected as favorite`() = runTest {
        coEvery { favoriteRocketRepository.updateFavoriteByRocketId(any()) } just runs

        val updateFavoriteRocketByIdUseCase = UpdateFavoriteRocketByIdUseCase(favoriteRocketRepository)
        updateFavoriteRocketByIdUseCase.invoke(rocketId)

        coVerify { favoriteRocketRepository.updateFavoriteByRocketId(rocketId) }
    }
}