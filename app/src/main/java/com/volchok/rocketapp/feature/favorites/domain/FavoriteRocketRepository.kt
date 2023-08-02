package com.volchok.rocketapp.feature.favorites.domain

import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.data.model.Data
import kotlinx.coroutines.flow.Flow

interface FavoriteRocketRepository {
    fun getFavoriteRockets(): Flow<List<RocketItem>>

    suspend fun setFavoriteRockets(rockets: List<RocketItem>, rocketId: String)

    suspend fun deleteFavoriteRocket()
}