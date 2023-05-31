package com.volchok.rocketapp.library.api.model.details

data class RocketDetailsModel(
    //val active: Boolean,
   // val boosters: Int,
   // val company: String,
   // val cost_per_launch: Int,
   // val country: String,
    val description: String? = null,
    val diameter: Diameter? = null,
    val engines: Engines? = null,
   // val first_flight: String,
    val first_stage: FirstStage? = null,
    val height: Height? = null,
   // val id: Int,
   // val landing_legs: LandingLegs,
    val mass: Mass? = null,
   // val payload_weights: List<PayloadWeight>,
    val rocket_id: String? = null,
    val rocket_name: String? = null,
    val rocket_type: String? = null,
    val second_stage: SecondStage? = null,
   // val stages: Int,
   // val success_rate_pct: Int,
   // val wikipedia: String
)