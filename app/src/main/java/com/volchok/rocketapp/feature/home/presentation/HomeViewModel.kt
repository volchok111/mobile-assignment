package com.volchok.rocketapp.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.rocketapp.feature.home.domain.OpenRocketInfoUseCase
import com.volchok.rocketapp.library.api.domain.ObserveRocketsUseCase
import com.volchok.rocketapp.library.api.model.home.RocketItem
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
        first_flight,
        rocket_name,
        rocket_id
    )

    data class State(
        val loading: Boolean = true,
        val rockets: List<RocketItem?> = emptyList()
    ) : AbstractViewModel.State {
        data class RocketItem(
            val first_flight: String? = null,
            val rocket_name: String? = null,
            val rocket_id: String? = null,
        )
    }
}