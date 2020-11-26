package com.example.movie.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel :  ViewModel(), Event {
    val eventLoading = MutableLiveData<Boolean>()
    val eventMessage = MutableLiveData<String>()
    val composite= CompositeDisposable()

    override fun showMessage(message: String) {
        eventMessage.value=message
    }

    override fun showLoading(loading: Boolean) {
        eventLoading.value=loading
    }

    override fun onCleared() {
        super.onCleared()
        composite.dispose()
    }

}