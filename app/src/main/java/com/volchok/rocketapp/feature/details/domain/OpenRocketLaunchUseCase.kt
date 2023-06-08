package com.volchok.rocketapp.feature.details.domain

import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase

class OpenRocketLaunchUseCase(
    private val rocketNavigationController: RocketNavigationController
) : SynchronousUseCase<Unit, Unit>{
    override fun invoke(input: Unit) {
        rocketNavigationController.goToLaunch()
    }
}