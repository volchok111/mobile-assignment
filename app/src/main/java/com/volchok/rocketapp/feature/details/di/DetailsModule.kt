package com.volchok.rocketapp.feature.details.di

import com.volchok.rocketapp.feature.details.domain.DeleteFavoriteUseCase
import com.volchok.rocketapp.feature.details.domain.GetFavoriteRocketsUseCase
import com.volchok.rocketapp.feature.details.domain.OpenRocketLaunchUseCase
import com.volchok.rocketapp.feature.details.domain.SaveToFavoriteUseCase
import com.volchok.rocketapp.feature.details.presentation.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val detailsModule = module {
    viewModelOf(::DetailsViewModel)
    factoryOf(::OpenRocketLaunchUseCase)
    factoryOf(::SaveToFavoriteUseCase)
    factoryOf(::GetFavoriteRocketsUseCase)
    factoryOf(::DeleteFavoriteUseCase)
}