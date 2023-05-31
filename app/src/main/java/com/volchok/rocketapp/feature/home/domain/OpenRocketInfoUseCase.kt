package com.volchok.rocketapp.feature.home.domain

import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase

class OpenRocketInfoUseCase(
    private val rocketsNavigationController: RocketsNavigationController
) : SynchronousUseCase<Int, Unit> {
    override fun invoke(input: Int) {
        rocketsNavigationController.goToRocketInfo()
    }
}