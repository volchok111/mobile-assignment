package com.volchok.rocketapp.feature.favorites.di

import com.volchok.rocketapp.feature.favorites.data.FavoritesRepository
import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val favoritesModule = module {
    factoryOf(::FavoritesRepository) bind FavoriteRocketRepository::class
}