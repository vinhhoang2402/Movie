package com.example.domain.common

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleParamUseCase<T, in Param>(
    private val uiThread: Scheduler,
    private val executorThread: Scheduler
) {
    //khong can goi ten function, phu hop voi lop chi co 1 pthuc cong khai
    operator fun invoke(param: Param): Single<T> {
        return create(param).subscribeOn(executorThread).observeOn(uiThread)
    }

    abstract fun create(param: Param): Single<T>
}