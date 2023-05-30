package com.volchok.rocketapp.app.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.rocketapp.app.domain.GoToHomeUseCase
import com.volchok.rocketapp.app.domain.ObserveNavigationEventsUseCase
import com.volchok.rocketapp.app.model.ForwardNavigationEvent
import com.volchok.rocketapp.app.model.NavigationEvent
import com.volchok.rocketapp.app.model.Route
import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val observeNavigationEventsUseCase: ObserveNavigationEventsUseCase,
    private val goToHomeUseCase: GoToHomeUseCase
) : AbstractViewModel<MainViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeNavigationEventsUseCase(Unit).collect { onNavigationEvent(it) }
        }
    }

    private fun onNavigationEvent(navigationEvent: NavigationEvent) {
        state = state.copy(navigationEvent = navigationEvent)
    }

    fun onNavigationEventConsumed() {
        state = state.copy(navigationEvent = null)
    }

    data class State(
        val navigationEvent: NavigationEvent? = ForwardNavigationEvent(Route.Initial)
    ) : AbstractViewModel.State
}