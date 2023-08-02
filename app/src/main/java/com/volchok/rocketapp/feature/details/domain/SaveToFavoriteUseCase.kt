package com.volchok.rocketapp.feature.details.domain

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import com.volchok.rocketapp.library.use_case.domain.SuspendUseCase

class SaveToFavoriteUseCase(
    private val favoriteRocketRepository: FavoriteRocketRepository
) : SuspendUseCase<List<FavoritesModel>, Unit> {
    override suspend fun invoke(input: List<FavoritesModel>) {
        favoriteRocketRepository.setFavoriteRockets(input)
    }
}