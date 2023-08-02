package com.volchok.rocketapp.feature.favorites.domain

import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import com.volchok.rocketapp.library.data.model.Data
import kotlinx.coroutines.flow.Flow

interface FavoriteRocketRepository {
    fun getFavoriteRockets(): Flow<List<FavoritesModel>>

    suspend fun setFavoriteRockets(rockets: List<FavoritesModel>)

    suspend fun deleteFavoriteRocket()
}