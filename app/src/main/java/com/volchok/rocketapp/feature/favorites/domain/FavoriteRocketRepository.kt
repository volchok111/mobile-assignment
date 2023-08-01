package com.volchok.rocketapp.feature.favorites.domain

import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRocketRepository {
    fun getFavoriteRockets(rockets: List<FavoritesModel>): Flow<List<FavoritesModel>>

    suspend fun setFavoriteRockets(rockets: List<FavoritesModel>)
}