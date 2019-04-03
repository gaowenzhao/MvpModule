package com.zhao.base.presenter

import android.content.Context
import com.zhao.base.inf.BasePresenterI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 * desc
 */
open class BasePresenter<V> : BasePresenterI {

    var mView: V? = null
    private var mCompositeSubscription: CompositeDisposable? = null

    fun attachView(view: V): BasePresenter<V> {
        this.mView = view
        return this
    }


    /**
     * 事件订阅
     */
    override fun addSubscription(s: DisposableObserver<*>) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = CompositeDisposable()
        }
        mCompositeSubscription?.add(s)
    }

    override fun unsubcrible() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription!!.clear()
        }
        mView = null
       mCompositeSubscription = null
    }
}
