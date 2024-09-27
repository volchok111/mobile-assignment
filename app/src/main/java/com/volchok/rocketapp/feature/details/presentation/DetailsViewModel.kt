package com.volchok.rocketapp.feature.details.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.rocketapp.feature.details.domain.OpenRocketLaunchUseCase
import com.volchok.rocketapp.feature.details.domain.UpdateFavoriteRocketByIdUseCase
import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.data.model.Data
import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel
import com.volchok.rocketapp.library.rockets.domain.FetchRocketInfoUseCase
import com.volchok.rocketapp.library.rockets.domain.ObserveRocketDetailsUseCase
import com.volchok.rocketapp.library.use_case.domain.invoke
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val fetchRocketInfoUseCase: FetchRocketInfoUseCase,
    private val observeRocketDetailsUseCase: ObserveRocketDetailsUseCase,
    private val openRocketLaunchUseCase: OpenRocketLaunchUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteRocketByIdUseCase
) : AbstractViewModel<DetailsViewModel.State>(State()) {

    init {
        loadDetails()

        viewModelScope.launch {
            observeRocketDetailsUseCase().collect {
                state = state.copy(rocket = it, loading = false)
            }
        }
    }

    fun onLikeClicked() {
        viewModelScope.launch {
            state.rocket?.rocket_id?.let {
                updateFavoriteUseCase.invoke(input = it)
                loadDetails()
            }
        }
    }

    private fun loadDetails() {
        viewModelScope.launch {
            val rocketResult = fetchRocketInfoUseCase()
            if (rocketResult is Data.Success) {
                state = state.copy(rocket = rocketResult.value, loading = false)
            } else {
                if (rocketResult is Data.Error) {
                    throw IllegalStateException(rocketResult.cause.toString())
                }
                throw IllegalStateException("Unknown exception")
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