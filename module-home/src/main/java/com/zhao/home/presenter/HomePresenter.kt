package com.zhao.home.presenter

import com.zhao.base.BaseObserver
import com.zhao.base.http.bean.BaseBean
import com.zhao.base.presenter.BasePresenter
import com.zhao.home.model.HomeModel

class HomePresenter : BasePresenter<HomeContract.View>(),HomeContract.Presenter{
    private val homeModel = HomeModel()
    override fun getBannerList() {
        addSubscription(homeModel.getBannerList(object :BaseObserver<Any>(){
            override fun doOnNext(t: BaseBean<Any>?) {

            }
        }))

    }
}