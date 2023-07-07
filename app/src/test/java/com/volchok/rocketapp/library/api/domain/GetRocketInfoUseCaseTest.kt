package com.volchok.rocketapp.library.api.domain

import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.data.model.Data
import com.volchok.rocketapp.library.rockets.domain.GetSelectedRocketIdUseCase
import com.volchok.rocketapp.library.use_case.domain.invoke
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class GetRocketInfoUseCaseTest {

    private val rocketRepository = mockk<RemoteRepository>()

    val getSelectedRocketIdUseCase = mockk<GetSelectedRocketIdUseCase>()

    @Test
    fun `should return rocket info`() = runTest {


        val testId = "falcon_heavy"
        val testRocketDetailsData = mockk<RocketDetailsModel>()
        every { testRocketDetailsData.rocket_id } returns testId

        coEvery { getSelectedRocketIdUseCase(Unit) } returns testId

        coEvery { rocketRepository.getRocketInfo(any()) } returns Data.Success(
            testRocketDetailsData
        )
        val getRocketInfoUseCase =
            GetRocketInfoUseCase(rocketRepository, getSelectedRocketIdUseCase)

        val result = getRocketInfoUseCase.invoke()

        result.first() shouldBe Data.Success(testRocketDetailsData)

        coVerify { rocketRepository.getRocketInfo(testId) }
    }
}