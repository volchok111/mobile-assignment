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
            observeRocketStageUseCase(Unit).collect { onRocketStageChanged(it) }
        }
    }

    private fun onRocketStageChanged(stage: RocketStages) {
        state = state.copy(isReady = stage == RocketStages.Start)
    }


    data class State(
        val test: String = "",
        val rocket: Bitmap? = null,
        val isReady: Boolean = false
    ) : AbstractViewModel.State


}