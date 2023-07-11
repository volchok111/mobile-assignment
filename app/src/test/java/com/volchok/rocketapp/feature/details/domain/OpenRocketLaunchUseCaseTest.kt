package com.volchok.rocketapp.feature.details.domain

import io.mockk.*
import org.junit.Test

internal class OpenRocketLaunchUseCaseTest {
    private val rocketNavigationController = mockk<RocketNavigationController>()

    @Test
    fun `verify if opens rocket launch screen` () {

        every { rocketNavigationController.goToLaunch() } just runs

        val openRocketLaunchUseCase = OpenRocketLaunchUseCase(rocketNavigationController)

        openRocketLaunchUseCase.invoke(Unit)

        verify { rocketNavigationController.goToLaunch() }
    }
}