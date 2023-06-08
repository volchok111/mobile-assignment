package com.volchok.rocketapp.feature.sensor.presentation

import android.graphics.Bitmap
import com.volchok.rocketapp.library.mvvm.presentation.AbstractViewModel

class RocketLaunchViewModel : AbstractViewModel<RocketLaunchViewModel.State>(State()) {




    data class State(
        val test: String = "",
        val rocket: Bitmap? = null
    ) : AbstractViewModel.State


}