package com.volchok.rocketapp.library.api.model.details

data class RocketDetailsModel(
    val active: Boolean,
    val boosters: Int,
    val company: String,
    val cost_per_launch: Int,
    val country: String,
    val description: String,
    val diameter: Diameter,
    val engines: Engines,
    val first_flight: String,
    val first_stage: FirstStage,
    val height: Height,
    val id: Int,
    val landing_legs: LandingLegs,
    val mass: Mass,
    val payload_weights: List<PayloadWeight>,
    val rocket_id: String,
    val rocket_name: String,
    val rocket_type: String,
    val second_stage: SecondStage,
    val stages: Int,
    val success_rate_pct: Int,
    val wikipedia: String
)