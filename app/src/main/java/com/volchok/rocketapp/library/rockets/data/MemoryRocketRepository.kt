package com.volchok.rocketapp.library.rockets.data

import com.volchok.rocketapp.library.rockets.domain.RocketRepository

class MemoryRocketRepository : RocketRepository {
    override var selectedRocketId: String = ""
}