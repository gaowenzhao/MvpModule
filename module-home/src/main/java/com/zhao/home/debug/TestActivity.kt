package com.zhao.home.debug

import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.view.WindowManager
import com.zhao.base.BaseActivity
import com.zhao.base.BasePresenter
import com.zhao.base.utils.StatusBarUtil
import com.zhao.home.R
import com.zhao.home.databinding.ActivityTestBinding

class TestActivity : BaseActivity<ActivityTestBinding,BasePresenter<*>>(){
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