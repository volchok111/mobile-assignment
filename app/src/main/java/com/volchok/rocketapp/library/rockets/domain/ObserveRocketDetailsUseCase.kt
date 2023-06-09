package com.volchok.rocketapp.library.rockets.domain

import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

class ObserveRocketDetailsUseCase(
    private val localRocketRepository: LocalRocketRepository
) : SynchronousUseCase<Unit, Flow<RocketDetailsModel>> {
    override fun invoke(input: Unit): Flow<RocketDetailsModel> = localRocketRepository.rocket
}