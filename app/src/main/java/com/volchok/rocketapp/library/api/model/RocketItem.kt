package com.volchok.rocketapp.library.api.model

data class RocketItem(
    val active: Boolean? = null,
    val boosters: Int? = null,
    val company: String? = null,
    val cost_per_launch: Int? = null,
    val country: String? = null,
    val description: String? = null,
    val diameter: Diameter? = null,
    val engines: Engines? = null,
    val first_flight: String? = null,
    val first_stage: FirstStage? = null,
    val height: Height? = null,
    val id: Int? = null,
    val landing_legs: LandingLegs? = null,
    val mass: Mass? = null,
    //val payload_weights: List<PayloadWeight> = emptyList(),
    val rocket_id: String? = null,
    val rocket_name: String? = null,
    val rocket_type: String? = null,
    val second_stage: SecondStage? = null,
    val stages: Int? = null,
    val success_rate_pct: Int? = null,
    val wikipedia: String? = null
)