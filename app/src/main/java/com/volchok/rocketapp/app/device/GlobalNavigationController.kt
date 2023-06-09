package com.volchok.rocketapp.app.device

import com.volchok.rocketapp.app.domain.MainNavigationController
import com.volchok.rocketapp.app.model.BackNavigationEvent
import com.volchok.rocketapp.app.model.ForwardNavigationEvent
import com.volchok.rocketapp.app.model.NavigationEvent
import com.volchok.rocketapp.app.model.Route
import com.volchok.rocketapp.feature.home.domain.HomeNavigationController
import com.volchok.rocketapp.feature.details.domain.RocketNavigationController
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class GlobalNavigationController :
    MainNavigationController,
    HomeNavigationController,
    RocketNavigationController {

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1)
    override val navigationEvent = _navigationEvent.asSharedFlow()

    override fun goBack() = goTo(BackNavigationEvent)

    override fun goToHome() = goTo(ForwardNavigationEvent(Route.Home, true))

    override fun goToRocketInfo() = goTo(ForwardNavigationEvent(Route.Details))

    override fun goToLaunch() = goTo(ForwardNavigationEvent(Route.RocketLaunch))

    private fun goTo(navigationEvent: NavigationEvent) {
        _navigationEvent.tryEmit(navigationEvent)
    }
}