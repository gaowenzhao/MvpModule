package com.zhao.main

import android.content.Intent
import android.os.Handler
import com.zhao.base.inf.BaseActivity
import com.zhao.base.presenter.BasePresenter
import com.zhao.main.databinding.SplashActivityBinding

class SplashActivity: BaseActivity<SplashActivityBinding, BasePresenter<*>>() {
    override var layoutId: Int = R.layout.splash_activity
    private val mHandler = Handler()
    override fun initView() {
        mHandler.postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },1500)
    }

    override fun initData() {
    }
}