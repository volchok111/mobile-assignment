package com.volchok.rocketapp.app.system

import android.app.Application
import com.volchok.rocketapp.app.di.mainModule
import com.volchok.rocketapp.feature.details.di.detailsModule
import com.volchok.rocketapp.feature.home.di.homeModule
import com.volchok.rocketapp.library.api.di.apiModule
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
                apiModule
            )
        }
        super.onCreate()
    }
}