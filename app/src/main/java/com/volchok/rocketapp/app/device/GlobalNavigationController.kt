package com.volchok.rocketapp.app.device

import com.volchok.rocketapp.app.domain.MainNavigationController
import com.volchok.rocketapp.app.model.BackNavigationEvent
import com.volchok.rocketapp.app.model.ForwardNavigationEvent
import com.volchok.rocketapp.app.model.NavigationEvent
import com.volchok.rocketapp.app.model.Route
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class GlobalNavigationController : MainNavigationController {

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1)
    override val navigationEvent = _navigationEvent.asSharedFlow()

    override fun goBack() = goTo(BackNavigationEvent)

    override fun goToSplash() = goTo(Route.Splash)

    override fun goToHome() = goTo(ForwardNavigationEvent(Route.Home, true))

    private fun goTo(route: Route) = goTo(ForwardNavigationEvent(route))

    private fun goTo(navigationEvent: NavigationEvent) {
        _navigationEvent.tryEmit(navigationEvent)
    }
}