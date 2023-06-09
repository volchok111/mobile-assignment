package com.volchok.rocketapp.library.api.model.details

data class RocketDetailsModel(
    val description: String? = null,
    val diameter: Diameter? = null,
    val first_stage: FirstStage? = null,
    val height: Height? = null,
    val id: Int,
    val mass: Mass? = null,
    val rocket_id: String? = null,
    val rocket_name: String? = null,
    val rocket_type: String? = null,
    val second_stage: SecondStage? = null,
    val flickr_images: List<String>,
)