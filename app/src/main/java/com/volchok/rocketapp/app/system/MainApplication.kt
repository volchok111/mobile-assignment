package com.volchok.rocketapp.app.system

import android.app.Application
import com.volchok.rocketapp.app.di.mainModule
import com.volchok.rocketapp.feature.details.di.detailsModule
import com.volchok.rocketapp.feature.home.di.homeModule
import com.volchok.rocketapp.feature.sensor.di.sensorModule
import com.volchok.rocketapp.library.api.di.apiModule
import com.volchok.rocketapp.library.networking.di.networkModule
import com.volchok.rocketapp.library.rockets.di.rocketModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        startKoin {
            androidContext(applicationContext)
            modules(
                mainModule,
                homeModule,
                detailsModule,
                apiModule,
                rocketModule,
                sensorModule,
                networkModule
            )
        }
        super.onCreate()
    }
}