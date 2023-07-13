package com.volchok.rocketapp.feature.home.domain

import com.volchok.rocketapp.library.rockets.domain.RocketRepository
import io.mockk.*
import org.junit.Test

internal class OpenRocketInfoUseCaseTest {

    private val homeNavigationController = mockk<HomeNavigationController>()
    private val rocketRepository = mockk<RocketRepository>()

    @Test
    fun `should open rocket info screen with a correct id`() {
        val rocketId = "falcon_heavy"

        every { rocketRepository.selectedRocketId = any() } just runs

        every { homeNavigationController.goToRocketInfo() } just runs

        val openRocketInfoUseCase =
            OpenRocketInfoUseCase(homeNavigationController, rocketRepository)
        openRocketInfoUseCase.invoke(rocketId)

        verify { homeNavigationController.goToRocketInfo() }

        verify { rocketRepository.selectedRocketId = rocketId }
    }
}