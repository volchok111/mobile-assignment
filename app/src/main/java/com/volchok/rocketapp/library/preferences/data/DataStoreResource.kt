package com.volchok.rocketapp.library.preferences.data

import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import kotlinx.coroutines.flow.Flow

interface DataStoreResource {
    fun observeRockets(favoriteRocket: List<FavoritesModel>): Flow<List<FavoritesModel>>

    suspend fun setRockets(favoriteRocket: List<FavoritesModel>)
}