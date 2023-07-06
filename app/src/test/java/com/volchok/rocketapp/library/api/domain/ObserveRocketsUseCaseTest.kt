package com.volchok.rocketapp.library.api.domain

import com.volchok.rocketapp.library.api.model.details.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock

internal class ObserveRocketsUseCaseTest {

    val rocketRepository = mock<RemoteRepository>()

    @Test
    fun `should return the same data as in repository`() {

        runBlocking {
            //  val testRocketData = RocketItem(rocket_id = "1", first_flight = "03.07.2023", rocket_name = "Falcon XXX")
            val testRocketDetailsData = RocketDetailsModel(
                description = "aaaa",
                diameter = Diameter(feet = 1, meters = 4.0),
                first_stage = FirstStage(burn_time_sec = 2, engines = 3, fuel_amount_tons = 50, reusable = true),
                height = Height(feet = 1.0, meters = 4),
                id = 111,
                mass = Mass(100, 200),
                rocket_id = "falcon_heavy",
                rocket_name = "Falcon",
                rocket_type = "heavy",
                second_stage = SecondStage(burn_time_sec = 8, engines = 4, fuel_amount_tons = 100, reusable = false),
                flickr_images = emptyList()
            )
           // Mockito.`when`(rocketRepository.getRocketInfo("")).thenReturn(testRocketDetailsData)
        }
    }
}