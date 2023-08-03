package com.volchok.rocketapp.feature.details.domain

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.library.use_case.domain.SuspendUseCase

class UpdateFavoritesUseCase(
    private val favoriteRocketRepository: FavoriteRocketRepository
) : SuspendUseCase<UpdateFavoritesUseCase.Params, Unit> {

    override suspend fun invoke(input: Params) {
        favoriteRocketRepository.updateFavoriteByRocketId(input.rocketId)
    }

    data class Params(
        val rocketId: String
    )
}