package com.volchok.rocketapp.feature.details.domain

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.use_case.domain.SuspendUseCase

class SaveToFavoriteUseCase(
    private val favoriteRocketRepository: FavoriteRocketRepository
) : SuspendUseCase<SaveToFavoriteUseCase.Params, Unit> {
    override suspend fun invoke(input: Params) {
        favoriteRocketRepository.setFavoriteRockets(input.rocketList, input.rocketId)
    }

    data class Params(
        val rocketList: List<RocketItem>,
        val rocketId: String
    )
}