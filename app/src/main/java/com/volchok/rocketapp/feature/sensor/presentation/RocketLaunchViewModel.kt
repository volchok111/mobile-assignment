package com.volchok.rocketapp.feature.sensor.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.rocketapp.feature.sensor.domain.ObserveRocketLaunchStageUseCase
import com.volchok.rocketapp.feature.sensor.model.RocketStages
import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel
import kotlinx.coroutines.launch

class RocketLaunchViewModel(
    private val observeRocketLaunchStageUseCase: ObserveRocketLaunchStageUseCase,
) : AbstractViewModel<RocketLaunchViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeRocketLaunchStageUseCase(Unit).collect { onRocketFlyingStage(it) }
        }
    }

    private fun onRocketFlyingStage(stage: RocketStages) {
        state = state.copy(isStageFlying = stage == RocketStages.FlyingStage)
    }

    data class State(
        val isStageFlying: Boolean = false,
    ) : AbstractViewModel.State
}