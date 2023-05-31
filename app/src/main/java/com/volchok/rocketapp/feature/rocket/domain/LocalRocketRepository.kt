package com.volchok.rocketapp.feature.rocket.domain

import com.volchok.rocketapp.library.api.model.RocketItem
import kotlinx.coroutines.flow.Flow

interface LocalRocketRepository {
    val rocket: Flow<RocketItem>

    fun set(rocket: RocketItem)
}