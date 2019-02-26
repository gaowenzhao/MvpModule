package com.zhao.home.debug

import com.zhao.base.inf.BaseActivity
import com.zhao.base.presenter.BasePresenter
import com.zhao.home.R
import com.zhao.home.databinding.ActivityMytestBinding

class MyTestActivity : BaseActivity<ActivityMytestBinding, BasePresenter<*>>(){
    override var layoutId: Int = R.layout.activity_mytest

    override fun initView() {
    }

    override fun initData() {
    }

}