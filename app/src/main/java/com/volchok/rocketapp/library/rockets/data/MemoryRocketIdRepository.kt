package com.volchok.rocketapp.library.rockets.data

import com.volchok.rocketapp.library.rockets.domain.RocketIdRepository

class MemoryRocketIdRepository : RocketIdRepository {
    override var selectedRocketId: String = ""
}