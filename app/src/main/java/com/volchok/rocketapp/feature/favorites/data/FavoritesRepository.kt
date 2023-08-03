package com.volchok.rocketapp.feature.favorites.data

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.preferences.data.DataStoreResource

class FavoritesRepository(
    private val dataStoreResource: DataStoreResource
) : FavoriteRocketRepository {

    override suspend fun saveFavoriteRockets(rockets: List<RocketItem>) {
        dataStoreResource.saveItems(rockets)
    }

    override suspend fun getFavoriteRockets(): List<RocketItem>? {
        return dataStoreResource.getItems()
    }

    override suspend fun getFavoriteRocketById(rocketId: String): RocketItem? {
        return dataStoreResource.getItemById(rocketId)
    }

    override suspend fun updateFavoriteByRocketId(rocketId: String) {
        dataStoreResource.updateFavoriteByItemId(rocketId)
    }
}