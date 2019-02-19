package com.zhao.home

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhao.base.BaseFragment
import com.zhao.base.BasePresenter
import com.zhao.base.router.RouterUrl
import com.zhao.home.databinding.FragmentHomeBinding


@Route(path = RouterUrl.Home.TabHome)
class HomeFragment : BaseFragment<FragmentHomeBinding, BasePresenter<*>>(){
     override var darkMode: Boolean = false
     override var layoutId: Int = R.layout.fragment_home
     override fun initData() {

     }
     override fun initView(RootView: View) {

     }
}