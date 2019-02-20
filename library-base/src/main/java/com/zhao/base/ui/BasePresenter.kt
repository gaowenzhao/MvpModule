package com.zhao.base.ui

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 * desc
 */
open class BasePresenter<V> : BasePresenterI {

    var mView: V? = null
    var context: Context? = null
    private var mCompositeSubscription: CompositeDisposable? = null

    fun attachView(view: V, context: Context): BasePresenter<V> {
        this.mView = view
        this.context = context
        return this
    }


    /**
     * 事件订阅
     */
    fun addSubscription(s: DisposableObserver<*>) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = CompositeDisposable()
        }
        this.mCompositeSubscription!!.add(s)
    }

    override fun unsubcrible() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription!!.clear()
        }
        mView = null
        context = null
        this.mCompositeSubscription = null
    }
}
