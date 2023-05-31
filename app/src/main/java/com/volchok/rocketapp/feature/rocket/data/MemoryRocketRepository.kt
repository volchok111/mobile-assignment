package com.volchok.rocketapp.feature.rocket.data

import com.volchok.rocketapp.feature.rocket.domain.RocketRepository

class MemoryRocketRepository: RocketRepository {
    override var selectedRocketId: String = ""
}