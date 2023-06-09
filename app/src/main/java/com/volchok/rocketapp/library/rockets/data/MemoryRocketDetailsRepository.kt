package com.volchok.rocketapp.library.rockets.data

import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.rockets.domain.LocalRocketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class MemoryRocketDetailsRepository : LocalRocketRepository {
    private val _rocket = MutableStateFlow<RocketDetailsModel?>(null)
    override val rocket: Flow<RocketDetailsModel> = _rocket.filterNotNull()

    override fun set(rocket: RocketDetailsModel) {
        _rocket.tryEmit(rocket)
    }
}