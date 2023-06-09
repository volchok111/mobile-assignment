package com.volchok.rocketapp.library.api.domain

import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.data.model.Data

interface RemoteRepository {

    suspend fun getRockets(): Data<List<RocketItem>>

    suspend fun getRocketInfo(id: String): Data<RocketDetailsModel>
}