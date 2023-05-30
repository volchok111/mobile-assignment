package com.volchok.rocketapp.feature.home.presentation

import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel

class HomeViewModel : AbstractViewModel<HomeViewModel.State>(State()) {

    data class State(
        val loading: Boolean = false
    ) : AbstractViewModel.State
}