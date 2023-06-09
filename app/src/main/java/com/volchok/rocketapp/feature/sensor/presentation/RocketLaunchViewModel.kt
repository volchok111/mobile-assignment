package com.volchok.rocketapp.feature.sensor.presentation

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.volchok.rocketapp.feature.sensor.RocketStages
import com.volchok.rocketapp.feature.sensor.domain.ObserveRocketStageUseCase
import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel
import kotlinx.coroutines.launch

class RocketLaunchViewModel(
    private val observeRocketStageUseCase: ObserveRocketStageUseCase
) : AbstractViewModel<RocketLaunchViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeRocketStageUseCase(Unit).collect { onRocketStartStage(it) }
        }

        viewModelScope.launch {
            observeRocketStageUseCase(Unit).collect { onRocketFlyingStage(it) }
        }
    }

    private fun onRocketStartStage(stage: RocketStages) {
        state = state.copy(isStageStart = stage == RocketStages.Start)
    }

    private fun onRocketFlyingStage(stage: RocketStages) {
        state = state.copy(isStageFlying = stage == RocketStages.Flying)
    }


    data class State(
        val test: String = "",
        val rocket: Bitmap? = null,
        val isStageStart: Boolean = false,
        val isStageFlying: Boolean = false,
    ) : AbstractViewModel.State


}