package com.zhao.home.model

import com.zhao.base.http.BaseObs
import com.zhao.base.http.Client
import com.zhao.base.http.bean.BaseBean
import com.zhao.base.model.BaseModel
import io.reactivex.observers.DisposableObserver

class HomeModel : BaseModel(){
    fun  getBannerList(observer: BaseObs<Any>) : DisposableObserver<BaseBean<Any>> {
        return sub(Client.api.getBannerList(),observer)
    }
}