package com.volchok.rocketapp.library.rockets.domain

import com.volchok.rocketapp.library.api.domain.RemoteRepository
import com.volchok.rocketapp.library.api.model.details.*
import com.volchok.rocketapp.library.data.model.Data
import com.volchok.rocketapp.library.use_case.domain.invoke
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class FetchRocketInfoUseCaseTest {

    private val rocketRepository = mockk<RemoteRepository>()
    private val localRocketRepository = mockk<LocalRocketRepository>()
    private val getSelectedRocketIdUseCase = mockk<GetSelectedRocketIdUseCase>()

    @Test
    fun `should fetch rocket info`() = runTest {
        val testId = "falcon_heavy"

        val testRocketDetailsData = RocketDetailsModel(
            description = "aaa",
            diameter = Diameter(2, 2.0),
            first_stage = FirstStage(burn_time_sec = 3, engines = 2, fuel_amount_tons = 4, reusable = true),
            height = Height(2.0, 2),
            id = 1,
            mass = Mass(2, 3),
            rocket_id = testId,
            rocket_name = "Falcon",
            rocket_type = "rocket",
            second_stage = SecondStage(burn_time_sec = 4, engines = 1, fuel_amount_tons = 6, reusable = false),
            flickr_images = emptyList()
        )

        coEvery { getSelectedRocketIdUseCase(Unit) } returns testId

        coEvery { localRocketRepository.rocket } returns flowOf(testRocketDetailsData)

        coEvery { localRocketRepository.set(any()) } just runs

        coEvery { rocketRepository.getRocketInfo(any()) } returns Data.Success(
            testRocketDetailsData
        )

        val fetchRocketInfoUseCase = FetchRocketInfoUseCase(
            rocketRepository,
            localRocketRepository,
            getSelectedRocketIdUseCase
        )

        val result = fetchRocketInfoUseCase.invoke()

        result.shouldBeInstanceOf<Data.Success<RocketDetailsModel>>()
        result.value shouldBe testRocketDetailsData

        coVerify { rocketRepository.getRocketInfo(testId) }
    }
}