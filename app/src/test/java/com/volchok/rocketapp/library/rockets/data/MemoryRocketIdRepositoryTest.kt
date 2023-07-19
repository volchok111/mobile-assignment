package com.volchok.rocketapp.library.rockets.data

import com.volchok.rocketapp.library.rockets.domain.RocketIdRepository
import io.mockk.*
import org.junit.Test

internal class MemoryRocketIdRepositoryTest {
    private val rocketIdRepository = mockk<RocketIdRepository>()
    private val rocketId = "falcon_heavy"

    @Test
    fun `should store selected rocket id`() {
        every { rocketIdRepository.selectedRocketId  } returns rocketId

        val repository = MemoryRocketIdRepository()
        repository.selectedRocketId = rocketId
    }
}