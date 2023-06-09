package com.volchok.rocketapp.feature.home.domain

import com.volchok.rocketapp.library.rockets.domain.RocketRepository
import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase

class OpenRocketInfoUseCase(
    private val homeNavigationController: HomeNavigationController,
    private val rocketRepository: RocketRepository
) : SynchronousUseCase<String, Unit> {
    override fun invoke(input: String) {
        rocketRepository.selectedRocketId = input
        homeNavigationController.goToRocketInfo()
    }
}