package com.volchok.rocketapp.app.domain

import com.volchok.rocketapp.app.model.NavigationEvent
import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

class ObserveNavigationEventsUseCase(
    private val mainNavigationController: MainNavigationController
) : SynchronousUseCase<Unit, Flow<NavigationEvent>> {

    override fun invoke(input: Unit): Flow<NavigationEvent> =
        mainNavigationController.navigationEvent
}