package com.volchok.rocketapp.library.rockets.domain

import com.volchok.rocketapp.library.api.domain.RemoteRepository
import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.data.model.Data
import com.volchok.rocketapp.library.data.model.safeCall
import com.volchok.rocketapp.library.use_case.domain.SuspendUseCase
import com.volchok.rocketapp.library.use_case.domain.invoke

class FetchRocketInfoUseCase(
    private val repository: RemoteRepository,
    private val localRocketRepository: LocalRocketRepository,
    private val getSelectedRocketIdUseCase: GetSelectedRocketIdUseCase
) : SuspendUseCase<Unit, Data<RocketDetailsModel>> {
    override suspend fun invoke(input: Unit): Data<RocketDetailsModel> = safeCall {
        rocketInfo()
    }

    private suspend fun rocketInfo(): RocketDetailsModel {
        val result = repository.getRocketInfo(getSelectedRocketIdUseCase()).getSuccessValueOrThrow()
        val rocket = RocketDetailsModel(
            description = result.description,
            diameter = result.diameter,
            first_stage = result.first_stage,
            height = result.height,
            mass = result.mass,
            rocket_id = result.rocket_id,
            rocket_name = result.rocket_name,
            rocket_type = result.rocket_type,
            second_stage = result.second_stage,
            flickr_images = result.flickr_images,
            id = result.id
        )
        localRocketRepository.set(rocket)
        return rocket
    }
}