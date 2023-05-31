package com.volchok.rocketapp.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.rocketapp.feature.home.domain.OpenRocketInfoUseCase
import com.volchok.rocketapp.library.api.domain.ObserveRocketsUseCase
import com.volchok.rocketapp.library.api.model.*
import com.volchok.rocketapp.library.data.model.Data
import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel
import com.volchok.rocketapp.library.use_case.domain.invoke
import kotlinx.coroutines.launch

class HomeViewModel(
    private val observeRocketsUseCase: ObserveRocketsUseCase,
    private val openRocketInfoUseCase: OpenRocketInfoUseCase
) : AbstractViewModel<HomeViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeRocketsUseCase().collect { list ->
                if (list is Data.Success) {
                    state = state.copy(rockets = list.value.map { it.toItem() }, loading = false)
                }
            }
        }
    }

    fun onItem(id: String) {
        openRocketInfoUseCase(id)
    }

    private fun RocketItem.toItem() = State.RocketItem(
        active,
        boosters,
        company,
        cost_per_launch,
        country,
        description,
        diameter,
        engines,
        first_flight,
        first_stage,
        height,
        id,
        landing_legs,
        mass,
        payload_weights,
        rocket_id,
        rocket_name,
        rocket_type,
        second_stage,
        stages,
        success_rate_pct,
        wikipedia
    )

    data class State(
        val loading: Boolean = true,
        val rockets: List<RocketItem> = emptyList()
    ) : AbstractViewModel.State {
        data class RocketItem(
            val active: Boolean = false,
            val boosters: Int = 0,
            val company: String = "",
            val cost_per_launch: Int = 0,
            val country: String = "",
            val description: String = "",
            val diameter: Diameter? = null,
            val engines: Engines? = null,
            val first_flight: String = "",
            val first_stage: FirstStage? = null,
            val height: Height? = null,
            val id: Int = 0,
            val landing_legs: LandingLegs? = null,
            val mass: Mass? = null,
            val payload_weights: List<PayloadWeight> = emptyList(),
            val rocket_id: String = "",
            val rocket_name: String = "",
            val rocket_type: String = "",
            val second_stage: SecondStage? = null,
            val stages: Int = 0,
            val success_rate_pct: Int = 0,
            val wikipedia: String = ""
        )

        data class RocketModel(
            val rocketModel: List<RocketItem> = emptyList()
        )
    }
}