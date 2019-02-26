package com.zhao.base.model;

import com.google.gson.Gson
import com.zhao.base.http.Httper
import com.zhao.base.utils.NetUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody

abstract class BaseModel {
    open var mediaTypeParse = "application/json;charset=UTF-8"

    fun getCache(): String {
        return if (NetUtils.isNetworkAvailable()) Httper.CACHE_CONTROL_AGE else Httper.CACHE_CONTROL_CACHE
    }

    fun <T : Any> sub(t: Observable<T>): Observable<T> {
        return t.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun <T : Any> sub(t: Observable<T>, obs: DisposableObserver<T>): DisposableObserver<T> {
        return sub(t).subscribeWith(obs)
    }

     fun createBody(map: Map<String, Any>): RequestBody {
        val json: String = Gson().toJson(map)
        val body: RequestBody = RequestBody.create(MediaType.parse(mediaTypeParse), json)
        return body
    }
}
