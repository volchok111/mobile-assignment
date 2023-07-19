package com.volchok.rocketapp.library.rockets.data

import com.volchok.rocketapp.library.api.model.details.*
import com.volchok.rocketapp.library.rockets.domain.LocalRocketRepository
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

internal class MemoryRocketDetailsRepositoryTest {
    private val localRocketRepository = mockk<LocalRocketRepository>()
    private val rocketId = "falcon_heavy"
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
        rocket_id = rocketId,
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
    fun `should set rocket details model`() {
        every { localRocketRepository.set(any()) } just runs
        every { localRocketRepository.rocket } returns flowOf(testRocketDetailsData)

        val repository = MemoryRocketDetailsRepository()
        repository.set(testRocketDetailsData)
    }
}