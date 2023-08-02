package com.volchok.rocketapp.feature.details.domain

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

class GetFavoriteRocketsUseCase(
    private val favoriteRocketRepository: FavoriteRocketRepository
): SynchronousUseCase<Unit, Flow<List<FavoritesModel>>> {
    override fun invoke(input: Unit): Flow<List<FavoritesModel>> {
        return favoriteRocketRepository.getFavoriteRockets()
    }
}