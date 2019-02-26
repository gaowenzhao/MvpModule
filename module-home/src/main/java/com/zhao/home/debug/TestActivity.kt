package com.zhao.home.debug

import android.os.Build
import android.support.annotation.RequiresApi
import com.zhao.base.inf.BaseActivity
import com.zhao.base.presenter.BasePresenter
import com.zhao.home.R
import com.zhao.home.databinding.ActivityTestBinding

class TestActivity : BaseActivity<ActivityTestBinding, BasePresenter<*>>(){
    override var layoutId: Int = R.layout.activity_test
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {
//        StatusBarUtil.setStatusBarMode(this, false, R.color.colorAccent)
        ui.toolbar.setNavigationOnClickListener {
             finish()
        }
    }
    override fun initData() {

    }
}