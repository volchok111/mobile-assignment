package com.volchok.rocketapp.feature.details.domain

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.library.use_case.domain.SuspendUseCase

class DeleteFavoriteUseCase(
    private val favoriteRocketRepository: FavoriteRocketRepository
): SuspendUseCase<Unit, Unit> {
    override suspend fun invoke(input: Unit) {
        favoriteRocketRepository.deleteFavoriteRocket()
    }
}