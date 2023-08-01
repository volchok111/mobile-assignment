package com.volchok.rocketapp.feature.details.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.rocketapp.feature.details.domain.OpenRocketLaunchUseCase
import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel
import com.volchok.rocketapp.library.rockets.domain.FetchRocketInfoUseCase
import com.volchok.rocketapp.library.rockets.domain.ObserveRocketDetailsUseCase
import com.volchok.rocketapp.library.use_case.domain.invoke
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val fetchRocketInfoUseCase: FetchRocketInfoUseCase,
    private val observeRocketDetailsUseCase: ObserveRocketDetailsUseCase,
    private val openRocketLaunchUseCase: OpenRocketLaunchUseCase,
    private val favoriteRocketRepository: FavoriteRocketRepository
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

//    fun loadData() {
//        viewModelScope.launch {
//            favoriteRocketRepository.getFavoriteRockets(state.favorites)
//        }
//    }

    fun setData() {
        viewModelScope.launch {
            favoriteRocketRepository.setFavoriteRockets(state.favorites)
        }
    }

    fun onOpenRocketLaunch() {
        openRocketLaunchUseCase()
    }

    data class State(
        val loading: Boolean = true,
        val rocket: RocketDetailsModel? = null,
        val favorites: List<FavoritesModel> = emptyList()
    ) : AbstractViewModel.State
}