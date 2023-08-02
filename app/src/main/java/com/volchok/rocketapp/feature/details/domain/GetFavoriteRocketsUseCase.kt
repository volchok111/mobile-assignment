package com.volchok.rocketapp.feature.details.domain

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

class GetFavoriteRocketsUseCase(
    private val favoriteRocketRepository: FavoriteRocketRepository
): SynchronousUseCase<Unit, Flow<List<RocketItem>>> {
    override fun invoke(input: Unit): Flow<List<RocketItem>> {
        return favoriteRocketRepository.getFavoriteRockets()
    }
}