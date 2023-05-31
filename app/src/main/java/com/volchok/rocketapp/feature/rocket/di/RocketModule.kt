package com.volchok.rocketapp.feature.rocket.di

import com.volchok.rocketapp.feature.rocket.data.MemoryRocketDetailsRepository
import com.volchok.rocketapp.feature.rocket.data.MemoryRocketRepository
import com.volchok.rocketapp.feature.rocket.domain.*
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val rocketModule = module {
    factoryOf(::FetchRocketInfoUseCase)
    factoryOf(::GetSelectedRocketIdUseCase)
    factoryOf(::ObserveRocketDetailsUseCase)

    singleOf(::MemoryRocketDetailsRepository) bind LocalRocketRepository::class
    singleOf(::MemoryRocketRepository) bind RocketRepository::class
}