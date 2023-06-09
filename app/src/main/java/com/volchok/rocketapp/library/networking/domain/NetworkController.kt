package com.volchok.rocketapp.library.networking.domain

import com.volchok.rocketapp.library.networking.model.NetworkConnection
import kotlinx.coroutines.flow.Flow

internal interface NetworkController {
    fun observeNetworkChange(): Flow<NetworkConnection>
}