package com.volchok.rocketapp.app.domain

import com.volchok.rocketapp.app.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

interface MainNavigationController {
    val navigationEvent: Flow<NavigationEvent>

    fun goBack()

    fun goToHome()

}