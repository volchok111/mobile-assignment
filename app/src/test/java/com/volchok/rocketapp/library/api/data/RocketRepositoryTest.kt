package com.volchok.rocketapp.library.api.data

import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.data.model.Data
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class RocketRepositoryTest {

    private val rocketApi = mockk<RocketApi>()
    private val testRocketItems = listOf(mockk<RocketItem>())
    private val testId = "falcon_heavy"
    private val testRocketDetailsData = mockk<RocketDetailsModel>()

    @Test
    fun `should observe rockets and return rocket items`() = runTest {
        coEvery { rocketApi.getRockets() } returns testRocketItems

        val repository = RocketRepository(rocketApi)
        val result = repository.getRockets()

        result shouldBe Data.Success(testRocketItems)
    }

    @Test
    fun `should observe rocket info and return rocket details`() = runTest {
        coEvery { rocketApi.getRocketInfo(any()) } returns testRocketDetailsData

        val repository = RocketRepository(rocketApi)
        val result = repository.getRocketInfo(testId)

        result shouldBe Data.Success(testRocketDetailsData)
    }
}