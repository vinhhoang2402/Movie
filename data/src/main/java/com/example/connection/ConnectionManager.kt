package com.example.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.subjects.PublishSubject

class ConnectionManager(private val context: Context) {
    var isConnected = false

    private val connectionSubject = PublishSubject.create<Boolean>()
    //emit isConnect from Register network

    init {
        checkNetworkConnection(context)
    }


    fun getConnectionStatus(): Observable<Boolean> {
        return Observable.create { emitter: ObservableEmitter<Boolean> ->
            emitter.onNext(false)
            emitter.onComplete()
        }.flatMap {
            connectionSubject
        }.map {
            Log.d("sss",it.toString())
            it
        }
    }

    private fun checkNetworkConnection(context: Context) {
        registerNetworkCallback(context)
    }

    private fun registerNetworkCallback(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cm.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isConnected = true
                    connectionSubject.onNext(isConnected)
                }

                override fun onLost(network: Network) {
                    isConnected = false
                    connectionSubject.onNext(isConnected)
                }
            })
        }
        return isConnected
    }
}