package com.volchok.rocketapp.library.networking.device

import com.volchok.rocketapp.library.networking.domain.NetworkController
import com.volchok.rocketapp.library.networking.model.NetworkConnection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class AndroidNetworkController : NetworkController {
    private val networkResult = MutableSharedFlow<NetworkConnection>(extraBufferCapacity = 1)

    fun onConnected(results: NetworkConnection) {
        networkResult.tryEmit(results)
    }

    override fun observeNetworkChange(): Flow<NetworkConnection> = networkResult
}