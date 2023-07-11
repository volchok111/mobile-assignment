//package com.volchok.rocketapp.feature.sensor.system
//
//import com.volchok.rocketapp.feature.sensor.device.AndroidSensorController
//import com.volchok.rocketapp.feature.sensor.domain.SensorController
//import com.volchok.rocketapp.feature.sensor.model.RocketStages
//import io.mockk.coEvery
//import io.mockk.mockk
//import kotlinx.coroutines.flow.flowOf
//import org.junit.Test
//
//internal class SensorDelegateTest {
//
//    private val sensorController = mockk<SensorController>()
//    private val androidSensorController = mockk<AndroidSensorController>()
//
//    @Test
//    fun `sensor stage is flying`() {
//        coEvery { sensorController.launchRocket() } returns flowOf(RocketStages.FlyingStage)
//    }
//}