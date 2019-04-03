package com.zhao.base.http

import android.content.ContentValues.TAG
import android.text.TextUtils
import android.util.Log
import com.google.gson.GsonBuilder
import com.zhao.base.app.BaseApplication
import com.zhao.base.utils.HeaderUtils
import com.zhao.base.utils.NetUtils
import com.zhao.base.utils.SPUtil
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Httper {

    val mTag = javaClass.simpleName
    //=======================================================
    private val gson = GsonBuilder().create()
    var token: String = HeaderUtils.getToken()

    //=======================================================
    const val CACHE_STALE_SEC = (60 * 60 * 24 * 2).toLong()//两天
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    const val CACHE_CONTROL_CACHE = "only-if-cached, max-stale=$CACHE_STALE_SEC"
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    const val CACHE_CONTROL_AGE = "max-age=0"
    //=======================================================
    private lateinit var retrofit: Retrofit
    //lateinit var restApi: BaseApi
    //网络超时时间（秒）
    private const val TIMEOUT = 15
    //=======================================================

    //=======================================================
    private lateinit var mOkHttpClient: OkHttpClient
    private val cacheSize = 1024 * 1024 * 100

    //===================== okhttp client===============================
    init {
        initOkHttpClient()
        initRetrofit()
    }

    private fun initOkHttpClient() {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.e(
            TAG,
            "OkHttp====message $message"
        )
        }).setLevel(HttpLoggingInterceptor.Level.BODY)

        val mRewriteCacheControlInterceptor = Interceptor { chain ->
            var request = chain.request()

            val updateRequest: Request
            if (NetUtils.isNetworkAvailable()) {
                // 有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                val cacheControl = request.cacheControl().toString()
                updateRequest = request.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .header("imei", HeaderUtils.getIMEI())
                        .header("client", HeaderUtils.getPlatform())
                        .header("version", HeaderUtils.getVersionName())
                        .header("token", token)
                        .build()
            }
            else {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                Log.e(TAG, "no network")

                updateRequest = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$CACHE_STALE_SEC")
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .header("imei", HeaderUtils.getIMEI())
                        .header("client", HeaderUtils.getPlatform())
                        .header("version", HeaderUtils.getVersionName())
                        .header("token", token)
                        .build()
            }
            chain.proceed(updateRequest)
        }
        val cache = Cache(File(BaseApplication.appContext.cacheDir, "HttpCache"),
                (cacheSize).toLong())
        mOkHttpClient = OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout((TIMEOUT).toLong(), TimeUnit.SECONDS)
                .readTimeout((TIMEOUT).toLong(), TimeUnit.SECONDS)
                .writeTimeout((TIMEOUT).toLong(), TimeUnit.SECONDS)
                //.addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(interceptor)
                .build()
    }

    //===================== retrofit ==============================
    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
                // 设置url头  ：http:www.shianyunduan.com   BuildConfig.API_HOST_URL
                .baseUrl(CommonUrl.URL)
                //设置返回类型为RXJava，或者回调
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //设置返回数据类型
//                .addConverterFactory(NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                //设置网络请求，默认httpclient
                .client(mOkHttpClient)
                .build()
    }

    fun <API : Any> createApi(classModel: Class<API>): API {
        return retrofit.create<API>(classModel)
    }

    fun updataToken(v: String) {
        token = v
        SPUtil.getInstance().put(SPUtil.KEY_TOCKEN, v)
    }

    fun isLogin(): Boolean {//已登录
        return !TextUtils.isEmpty(token)
    }
}
