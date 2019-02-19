package com.zhao.base.debug
import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.zhao.base.BuildConfig

class DebugApplication :Application(){
    override fun onCreate() {
        super.onCreate()
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