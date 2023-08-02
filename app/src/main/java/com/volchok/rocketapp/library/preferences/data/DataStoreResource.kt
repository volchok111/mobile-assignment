package com.volchok.rocketapp.library.preferences.data

import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import com.volchok.rocketapp.library.data.model.Data
import kotlinx.coroutines.flow.Flow

interface DataStoreResource {
    fun observeItems(): Flow<List<FavoritesModel>>

    suspend fun saveItems(favoriteRocket: List<FavoritesModel>)

    suspend fun deleteItems()
}