package com.volchok.rocketapp.feature.rocket.domain

import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import kotlinx.coroutines.flow.Flow

interface LocalRocketRepository {
    val rocket: Flow<RocketDetailsModel>

    fun set(rocket: RocketDetailsModel)
}