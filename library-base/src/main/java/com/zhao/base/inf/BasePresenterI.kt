package com.zhao.base.inf

import io.reactivex.observers.DisposableObserver

/**
 *
 */
interface BasePresenterI {
    fun unsubcrible()
    fun addSubscription(s: DisposableObserver<*>)
}
