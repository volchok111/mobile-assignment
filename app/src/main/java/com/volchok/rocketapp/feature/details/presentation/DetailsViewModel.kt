package com.volchok.rocketapp.feature.details.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.rocketapp.feature.details.domain.OpenRocketLaunchUseCase
import com.volchok.rocketapp.library.rockets.domain.FetchRocketInfoUseCase
import com.volchok.rocketapp.library.rockets.domain.ObserveRocketDetailsUseCase
import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel
import com.volchok.rocketapp.library.use_case.domain.invoke
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val fetchRocketInfoUseCase: FetchRocketInfoUseCase,
    private val observeRocketDetailsUseCase: ObserveRocketDetailsUseCase,
    // private val getRocketInfoUseCase: GetRocketInfoUseCase
    private val openRocketLaunchUseCase: OpenRocketLaunchUseCase
) : AbstractViewModel<DetailsViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            fetchRocketInfoUseCase()
        }

        viewModelScope.launch {
            observeRocketDetailsUseCase().collect {
                state = state.copy(rocket = it, loading = false)
            }
        }
    }

    fun onOpenRocketLaunch() {
        openRocketLaunchUseCase()
    }

    data class State(
        val loading: Boolean = true,
        val rocket: RocketDetailsModel? = null
    ) : AbstractViewModel.State
}