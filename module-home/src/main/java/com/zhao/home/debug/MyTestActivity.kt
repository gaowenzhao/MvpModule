package com.zhao.home.debug

import com.zhao.base.BaseActivity
import com.zhao.base.BasePresenter
import com.zhao.base.utils.StatusBarUtil
import com.zhao.home.R
import com.zhao.home.databinding.ActivityMytestBinding

class MyTestActivity :BaseActivity<ActivityMytestBinding,BasePresenter<*>>(){
    override var layoutId: Int = R.layout.activity_mytest

    override fun initView() {
        StatusBarUtil.setDarkMode(this,true)
    }

    override fun initData() {
    }

}