package com.volchok.rocketapp.feature.details.domain

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.use_case.domain.SuspendUseCase

class SaveFavoritesUseCase(
    private val favoriteRocketRepository: FavoriteRocketRepository
) : SuspendUseCase<SaveFavoritesUseCase.Params, Unit> {

    override suspend fun invoke(input: Params) {
        favoriteRocketRepository.saveFavoriteRockets(input.rocketList)
    }

    data class Params(
        val rocketList: List<RocketItem>
    )
}