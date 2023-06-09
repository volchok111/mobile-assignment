package com.volchok.rocketapp.app.model

enum class Route {
    Home,
    Details,
    RocketLaunch;

    operator fun invoke() = name.lowercase()

    companion object {
        val Initial = Home
    }
}