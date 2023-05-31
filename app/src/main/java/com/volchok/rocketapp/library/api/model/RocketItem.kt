package com.volchok.rocketapp.library.api.model

data class RocketItem(
    val active: Boolean = false,
    val boosters: Int = 0,
    val company: String = "",
    val cost_per_launch: Int = 0,
    val country: String = "",
    val description: String = "",
    val diameter: Diameter? = null,
    val engines: Engines? = null,
    val first_flight: String = "",
    val first_stage: FirstStage? = null,
    val height: Height? = null,
    val id: Int = 0,
    val landing_legs: LandingLegs? = null,
    val mass: Mass? = null,
    val payload_weights: List<PayloadWeight> = emptyList(),
    val rocket_id: String = "",
    val rocket_name: String = "",
    val rocket_type: String = "",
    val second_stage: SecondStage? = null,
    val stages: Int = 0,
    val success_rate_pct: Int = 0,
    val wikipedia: String = ""
)