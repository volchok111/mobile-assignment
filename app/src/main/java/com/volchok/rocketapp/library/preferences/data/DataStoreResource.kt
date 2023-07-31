package com.volchok.rocketapp.library.preferences.data

import com.volchok.rocketapp.library.api.model.home.RocketItem
import kotlinx.coroutines.flow.Flow

interface DataStoreResource {
    fun observeRockets(favoriteRocket: List<RocketItem>): Flow<List<RocketItem>>

    suspend fun setRockets(favoriteRocket: List<RocketItem>)
}