package com.volchok.rocketapp.app.model

enum class Route {
    Splash,
    Home;

    operator fun invoke() = name.lowercase()


    //TODO initial should be Splash Screen
    companion object {
        val Initial = Home
    }
}