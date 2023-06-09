package com.volchok.rocketapp.library.networking.system

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.volchok.rocketapp.library.networking.device.AndroidNetworkController
import com.volchok.rocketapp.library.networking.model.NetworkConnection

class NetworkDelegate internal constructor(
    private val networkController: AndroidNetworkController,
    private val context: Context
) {
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            networkController.onConnected(NetworkConnection.Online)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            networkController.onConnected(NetworkConnection.Offline)
        }
    }

    fun onCreate() {
        getConnectivityManager().registerDefaultNetworkCallback(networkCallback)
    }

    fun onDestroy() {
        getConnectivityManager().unregisterNetworkCallback(networkCallback)
    }

    private fun getConnectivityManager(): ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java)
    }
}