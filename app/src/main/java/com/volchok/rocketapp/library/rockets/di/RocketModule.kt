package com.volchok.rocketapp.library.rockets.di

import com.volchok.rocketapp.library.rockets.data.MemoryRocketDetailsRepository
import com.volchok.rocketapp.library.rockets.data.MemoryRocketRepository
import com.volchok.rocketapp.library.rockets.domain.*
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