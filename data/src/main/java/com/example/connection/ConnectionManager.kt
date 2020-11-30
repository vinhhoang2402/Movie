package com.example.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build

class ConnectionManager(context: Context) {
    var isConnected = false

    init {
        checkNetworkConnection(context)
    }

    private fun checkNetworkConnection(context: Context) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        registerNetworkCallback(context)
    }

    private fun registerNetworkCallback(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cm.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isConnected = true
                }

                override fun onLost(network: Network) {
                    isConnected = false
                }
            })
        }
        return isConnected
    }
}