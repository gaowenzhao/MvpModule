package com.zhao.base.http

import com.zhao.base.http.bean.BaseBean
import io.reactivex.Observable
import retrofit2.http.GET
interface RestApi {
    /**
     * 首页轮播图
     */
    @GET("/hzed-qmwallet-app/advertisement/bannerList")
    fun getBannerList(): Observable<BaseBean<Any>>
}