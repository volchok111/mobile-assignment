package com.volchok.rocketapp.feature.rocket.domain

import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase

class GetSelectedRocketIdUseCase(
    private val repository: RocketRepository
) : SynchronousUseCase<Unit, String> {
    override fun invoke(input: Unit): String = repository.selectedRocketId
}