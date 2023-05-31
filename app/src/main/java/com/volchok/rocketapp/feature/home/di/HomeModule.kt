package com.volchok.rocketapp.feature.home.di

import com.volchok.rocketapp.feature.home.domain.OpenRocketInfoUseCase
import com.volchok.rocketapp.feature.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
    factoryOf(::OpenRocketInfoUseCase)
}