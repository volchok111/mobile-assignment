package com.volchok.rocketapp.library.networking.domain

import com.volchok.rocketapp.library.networking.model.NetworkConnection
import com.volchok.rocketapp.library.use_case.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

class ObserveConnectionUseCase internal constructor(
    private val networkController: NetworkController,
) : SynchronousUseCase<Unit, Flow<NetworkConnection>> {

    override fun invoke(input: Unit): Flow<NetworkConnection> =
        networkController.observeNetworkChange()
}