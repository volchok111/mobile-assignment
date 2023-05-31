package com.volchok.rocketapp.feature.details.presentation

import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel

class DetailsViewModel : AbstractViewModel<DetailsViewModel.State>(State()) {

    data class State(
        val loading: Boolean = false
    ) : AbstractViewModel.State
}