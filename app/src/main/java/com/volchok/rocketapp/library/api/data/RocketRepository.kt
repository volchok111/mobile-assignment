package com.volchok.rocketapp.library.api.data

import com.volchok.rocketapp.library.api.domain.RemoteRepository
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.data.model.Data

class RocketRepository(
    private val rocketApi: RocketApi
) : RemoteRepository {

    override suspend fun getRockets(): Data<List<RocketItem>> {
        return try {
            val result = rocketApi.getRockets()
            Data.Success(result)
        } catch (ex: Exception) {
            Data.Error(cause = ex)
        }
    }

    override suspend fun getRocketInfo(id: String): Data<RocketDetailsModel> {
        return try {
            val result = rocketApi.getRocketInfo(id)
            Data.Success(result)
        } catch (ex: Exception) {
            Data.Error(cause = ex)
        }
    }

}