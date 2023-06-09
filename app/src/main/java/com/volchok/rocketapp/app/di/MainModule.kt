package com.volchok.rocketapp.app.di

import com.volchok.rocketapp.app.device.GlobalNavigationController
import com.volchok.rocketapp.app.domain.MainNavigationController
import com.volchok.rocketapp.app.domain.ObserveNavigationEventsUseCase
import com.volchok.rocketapp.app.presentation.MainViewModel
import com.volchok.rocketapp.feature.details.domain.RocketNavigationController
import com.volchok.rocketapp.feature.home.domain.HomeNavigationController
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.binds
import org.koin.dsl.module

internal val mainModule = module {
    viewModelOf(::MainViewModel)
    factory { ObserveNavigationEventsUseCase(get()) }

    single { GlobalNavigationController() }.binds(
        arrayOf(
            MainNavigationController::class,
            HomeNavigationController::class,
            RocketNavigationController::class
        )
    )
}