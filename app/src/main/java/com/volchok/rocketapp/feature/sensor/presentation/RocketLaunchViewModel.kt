package com.volchok.rocketapp.feature.sensor.presentation

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.volchok.rocketapp.feature.sensor.domain.ObserveRocketStageUseCase
import com.volchok.rocketapp.feature.sensor.model.RocketStages
import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel
import kotlinx.coroutines.launch

class RocketLaunchViewModel(
    private val observeRocketStageUseCase: ObserveRocketStageUseCase
) : AbstractViewModel<RocketLaunchViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeRocketStageUseCase(Unit).collect { onRocketFlyingStage(it) }
        }
    }

    private fun onRocketFlyingStage(stage: RocketStages) {
        state = state.copy(isStageFlying = stage == RocketStages.FlyingStage)
    }

    data class State(
        val rocket: Bitmap? = null,
        val isStageFlying: Boolean = false,
    ) : AbstractViewModel.State
}