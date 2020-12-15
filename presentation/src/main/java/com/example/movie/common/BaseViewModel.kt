package com.example.movie.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(){
    val eventLoading = MutableLiveData<Event<Boolean>>()
    val eventMessage = MutableLiveData<Event<String>>()
    val composite = CompositeDisposable()
    fun showLoading(value: Boolean) {
        eventLoading.value = Event(value)
    }

    fun showMessage(message: String) {
        eventMessage.value = Event(message)
    }

    override fun onCleared() {
        super.onCleared()
        composite.dispose()
    }

}