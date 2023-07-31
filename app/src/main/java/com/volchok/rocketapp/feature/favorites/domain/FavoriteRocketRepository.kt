package com.volchok.rocketapp.feature.favorites.domain

import com.volchok.rocketapp.library.api.model.home.RocketItem
import kotlinx.coroutines.flow.Flow

interface FavoriteRocketRepository {
    fun getFavoriteRockets(rockets: List<RocketItem>): Flow<List<RocketItem>>

    suspend fun setFavoriteRockets(rockets: List<RocketItem>)
}