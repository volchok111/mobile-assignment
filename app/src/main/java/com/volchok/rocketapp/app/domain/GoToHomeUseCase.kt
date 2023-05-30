package com.volchok.rocketapp.app.domain

import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase

class GoToHomeUseCase(
    private val mainNavigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit>{
    override fun invoke(input: Unit) {
        mainNavigationController.goToHome()
    }
}