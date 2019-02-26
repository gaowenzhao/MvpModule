package com.zhao.base.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.zhao.base.BuildConfig
open class BaseApplication :Application(){
    companion object {
        @JvmStatic
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = this
        initARouter()
    }
    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
 }