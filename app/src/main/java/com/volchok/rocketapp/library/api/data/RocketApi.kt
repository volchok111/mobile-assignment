package com.volchok.rocketapp.library.api.data

import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketApi {

    @GET("rockets")
    suspend fun getRockets(): List<RocketItem>

    @GET("rockets/{rocket_id}")
    suspend fun getRocketInfo(
        @Path(value = "rocket_id") id: String
    ): RocketDetailsModel
}