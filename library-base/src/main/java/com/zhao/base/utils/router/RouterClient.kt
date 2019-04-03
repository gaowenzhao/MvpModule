package com.zhao.base.utils.router

import android.os.Bundle
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

object RouterClient {
    fun build(path: String): Postcard {
        return ARouter.getInstance().build(path)
    }

    fun start(path: String, bundle: Bundle? = null):Any{
        return start(ARouter.getInstance().build(path).with(bundle))
    }

    private fun start(build: Postcard):Any{
       return build.navigation()
    }
}