package com.volchok.rocketapp.app.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.rocketapp.app.domain.GoToHomeUseCase
import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel
import kotlinx.coroutines.launch

class SplashViewModel(
    private val goToHomeUseCase: GoToHomeUseCase
) : AbstractViewModel<SplashViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            goToHomeUseCase(Unit)
        }
    }

    data class State(
        val test: String = ""
    ) : AbstractViewModel.State
}