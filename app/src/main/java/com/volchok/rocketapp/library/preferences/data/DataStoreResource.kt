package com.volchok.rocketapp.library.preferences.data

import com.volchok.rocketapp.library.api.model.home.RocketItem

interface DataStoreResource {

    suspend fun saveItems(favoriteRocket: List<RocketItem>)

    suspend fun getItems() : List<RocketItem>?

    suspend fun deleteItems()

    suspend fun getItemById(rocketId: String): RocketItem?

    suspend fun updateFavoriteByItemId(rocketId: String)
}