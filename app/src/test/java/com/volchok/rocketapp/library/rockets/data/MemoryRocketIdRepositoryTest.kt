package com.volchok.rocketapp.library.rockets.data

import io.kotest.matchers.shouldBe
import org.junit.Test

internal class MemoryRocketIdRepositoryTest {
    private val rocketId = "falcon_heavy"

    @Test
    fun `should store default empty rocket id`() {

        val repository = MemoryRocketIdRepository()
        repository.selectedRocketId shouldBe ""
    }

    @Test
    fun `should store selected rocket id`() {
        val repository = MemoryRocketIdRepository()

        repository.selectedRocketId = rocketId
        repository.selectedRocketId shouldBe rocketId
    }
}