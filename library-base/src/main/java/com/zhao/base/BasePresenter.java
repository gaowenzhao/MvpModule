package com.zhao.base;

import android.content.Context;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * desc
 */
public class BasePresenter<V extends BaseViewI> implements BasePresenterI{

    public V mView;
    public Context context;
    private CompositeDisposable mCompositeSubscription;

    public BasePresenter<V> attachView(V view, Context context){
        this.mView = view;
        this.context = context;
        return this;
    }

    /**
     * 事件订阅
     */
    public void sub(DisposableObserver s){
        addSubscription(s);
    }

    public void addSubscription(DisposableObserver s){
        if(this.mCompositeSubscription == null){
            this.mCompositeSubscription = new CompositeDisposable();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void unsubcrible(){
        if(this.mCompositeSubscription != null){
            this.mCompositeSubscription.clear();
        }
        mView = null;
        context = null;
        this.mCompositeSubscription = null;
    }
}
