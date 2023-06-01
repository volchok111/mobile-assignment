package com.volchok.rocketapp.feature.rocket.domain

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
//        val result = repository.getRocketInfo(getSelectedRocketIdUseCase())
//        if (result is Data.Error) {
//            return result
//        } else if (result is Data.Success) {
//            val rocket = RocketDetailsModel(
//                description = result.value.description,
//                diameter = result.value.diameter,
//                engines = result.value.engines,
//                first_stage = result.value.first_stage,
//                height = result.value.height,
//                mass = result.value.mass,
//                rocket_id = result.value.rocket_id,
//                rocket_name = result.value.rocket_name,
//                rocket_type = result.value.rocket_type,
//                second_stage = result.value.second_stage,
//                flickr_images = result.value.flickr_images
//            )
//            localRocketRepository.set(rocket)
//            return Data.Success(rocket)
//        }
//        throw IllegalStateException("Invalid state for fetch rocket")
    }


    private suspend fun rocketInfo(): RocketDetailsModel {
        val result = repository.getRocketInfo(getSelectedRocketIdUseCase()).getSuccessValueOrThrow()
        val rocket = RocketDetailsModel(
            description = result.description,
            diameter = result.diameter,
            engines = result.engines,
            first_stage = result.first_stage,
            height = result.height,
            mass = result.mass,
            rocket_id = result.rocket_id,
            rocket_name = result.rocket_name,
            rocket_type = result.rocket_type,
            second_stage = result.second_stage,
            flickr_images = result.flickr_images
        )
        localRocketRepository.set(rocket)
        return rocket
    }
}