package com.example.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class ConnectionManager(private val context: Context) {
    var isConnected : Boolean= false

    private val connectionSubject =
        PublishSubject.create<Boolean>()

    init {
        checkNetworkConnection(context)
    }

    private fun checkConnection(emitter: ObservableEmitter<Boolean>) {
        checkNetworkConnection(context)
        emitter.onNext(isConnected)
        emitter.onComplete()
    }

    fun getConnectionStatus(): Observable<Boolean> {
        return Observable.create { emitter: ObservableEmitter<Boolean> ->
            checkConnection(emitter)
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