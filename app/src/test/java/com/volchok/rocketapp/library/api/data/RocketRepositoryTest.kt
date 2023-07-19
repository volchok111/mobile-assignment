package com.volchok.rocketapp.library.api.data

import com.volchok.rocketapp.library.api.model.details.*
import com.volchok.rocketapp.library.api.model.home.RocketItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class RocketRepositoryTest {

    private val rocketApi = mockk<RocketApi>()
    private val testRocketItems = mockk<List<RocketItem>>()
    private val testId = "falcon_heavy"
    private val testRocketDetailsData = RocketDetailsModel(
        description = "aaa",
        diameter = Diameter(2, 2.0),
        first_stage = FirstStage(
            burn_time_sec = 3,
            engines = 2,
            fuel_amount_tons = 4,
            reusable = true
        ),
        height = Height(2.0, 2),
        id = 1,
        mass = Mass(2, 3),
        rocket_id = testId,
        rocket_name = "Falcon",
        rocket_type = "rocket",
        second_stage = SecondStage(
            burn_time_sec = 4,
            engines = 1,
            fuel_amount_tons = 6,
            reusable = false
        ),
        flickr_images = emptyList()
    )

    @Test
    fun `should observe rockets and return rocket items`() = runTest {
        coEvery { rocketApi.getRockets() } returns testRocketItems

        val repository = RocketRepository(rocketApi)
        repository.getRockets()

        coVerify { rocketApi.getRockets() }
    }

    @Test
    fun `should observe rocket info and return rocket details`() = runTest {
        coEvery { rocketApi.getRocketInfo(any()) } returns testRocketDetailsData

        val repository = RocketRepository(rocketApi)
        repository.getRocketInfo(testId)

        coVerify { rocketApi.getRocketInfo(testId) }
    }
}