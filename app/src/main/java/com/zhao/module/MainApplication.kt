package com.zhao.module

import android.content.Context
import android.util.Log
import com.zhao.base.app.BaseApplication

class MainApplication : BaseApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.i("MainApplication","time=${System.currentTimeMillis()}")
    }
}