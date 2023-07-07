package com.volchok.rocketapp.library.rockets.domain

import com.volchok.rocketapp.library.use_case.domain.invoke
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

internal class GetSelectedRocketIdUseCaseTest {

    private val repository = mockk<RocketRepository>()

    @Test
    fun `should return the same rocket id`() {

        val rocketId = "falcon_heavy"

        every { repository.selectedRocketId } returns rocketId

        val getSelectedRocketIdUseCase = GetSelectedRocketIdUseCase(repository)

        val result = getSelectedRocketIdUseCase.invoke()

        result shouldBe rocketId
    }
}