package com.volchok.rocketapp.library.api.model

data class SecondStage(
    val burn_time_sec: Int,
    val engines: Int,
    val fuel_amount_tons: Double,
    val payloads: Payloads,
    val thrust: Thrust
)