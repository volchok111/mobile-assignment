package com.volchok.rocketapp.app.di

import com.volchok.rocketapp.app.device.GlobalNavigationController
import com.volchok.rocketapp.app.domain.GoToHomeUseCase
import com.volchok.rocketapp.app.domain.MainNavigationController
import com.volchok.rocketapp.app.domain.ObserveNavigationEventsUseCase
import com.volchok.rocketapp.app.presentation.MainViewModel
import com.volchok.rocketapp.app.presentation.SplashViewModel
import com.volchok.rocketapp.feature.home.domain.RocketsNavigationController
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.binds
import org.koin.dsl.module

internal val mainModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::SplashViewModel)
    factory { ObserveNavigationEventsUseCase(get()) }

    single { GlobalNavigationController() }.binds(
        arrayOf(
            MainNavigationController::class,
            RocketsNavigationController::class
        )
    )

    factoryOf(::GoToHomeUseCase)
}