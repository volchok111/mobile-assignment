package com.volchok.rocketapp.feature.favorites.domain

import com.volchok.rocketapp.library.api.model.home.RocketItem

interface FavoriteRocketRepository {

    suspend fun saveFavoriteRockets(rockets: List<RocketItem>)

    suspend fun getFavoriteRockets(): List<RocketItem>?

    suspend fun getFavoriteRocketById(rocketId: String): RocketItem?

    suspend fun updateFavoriteByRocketId(rocketId: String)
}