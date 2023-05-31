package com.volchok.rocketapp.feature.rocket.data

import com.volchok.rocketapp.feature.rocket.domain.LocalRocketRepository
import com.volchok.rocketapp.library.api.model.RocketItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class MemoryRocketRepository : LocalRocketRepository {
    private val _rocket = MutableStateFlow<RocketItem?>(null)
    override val rocket: Flow<RocketItem> = _rocket.filterNotNull()

    override fun set(rocket: RocketItem) {
        _rocket.tryEmit(rocket)
    }
}