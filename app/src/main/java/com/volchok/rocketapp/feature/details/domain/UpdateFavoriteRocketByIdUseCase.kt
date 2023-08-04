package com.volchok.rocketapp.feature.details.domain

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.library.use_case.domain.SuspendUseCase

class UpdateFavoriteRocketByIdUseCase(
    private val favoriteRocketRepository: FavoriteRocketRepository
) : SuspendUseCase<String, Unit> {

    override suspend fun invoke(input: String) {
        favoriteRocketRepository.updateFavoriteByRocketId(input)
    }
}