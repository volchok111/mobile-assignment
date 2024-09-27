package com.volchok.rocketapp.library.preferences.di

import com.volchok.rocketapp.library.preferences.data.DataStoreResource
import com.volchok.rocketapp.library.preferences.system.AndroidDataStoreResource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataStoreModule = module {
    factoryOf(::AndroidDataStoreResource) bind DataStoreResource::class
}