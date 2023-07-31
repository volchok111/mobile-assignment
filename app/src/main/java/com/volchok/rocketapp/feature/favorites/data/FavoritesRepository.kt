package com.volchok.rocketapp.feature.favorites.data

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.preferences.data.DataStoreResource
import kotlinx.coroutines.flow.Flow

class FavoritesRepository(
    private val dataStoreResource: DataStoreResource
) : FavoriteRocketRepository {
    override fun getFavoriteRockets(rockets: List<RocketItem>): Flow<List<RocketItem>> {
        return dataStoreResource.observeRockets(rockets)
    }

    override suspend fun setFavoriteRockets(rockets: List<RocketItem>) {
        dataStoreResource.setRockets(rockets)
    }
}