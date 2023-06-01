package com.volchok.rocketapp.library.api.model.details

data class SecondStage(
    val burn_time_sec: Int,
    val engines: Int,
    val fuel_amount_tons: Int,
    val reusable: Boolean
)