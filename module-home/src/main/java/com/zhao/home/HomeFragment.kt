package com.zhao.home

import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.zhao.base.inf.BaseFragment
import com.zhao.base.utils.router.RouterUrl
import com.zhao.home.databinding.FragmentHomeBinding
import com.zhao.home.presenter.HomeContract
import com.zhao.home.presenter.HomePresenter


@Route(path = RouterUrl.Home.TabHome)
class HomeFragment : BaseFragment<FragmentHomeBinding, HomePresenter>(),HomeContract.View{
     override var mPresenter: HomePresenter? = HomePresenter()
     override var darkMode: Boolean = false
     override var fitsSystemWindows: Boolean = true
     override var layoutId: Int = R.layout.fragment_home
     override var statusBarColor: Int = R.color.colorPrimary
     override fun initData() {
          mPresenter?.getBannerList()
          Glide.with(context)
               .load("http://hzedcom.oss-cn-shenzhen.aliyuncs.com/177918300695961600.png")
               .into(ui.ivBanner)
     }
     override fun initView(RootView: View) {
          mPresenter?.attachView(this)
          ui.toolbar.title = ""
     }
     override fun onBannerSuccess() {
     }

     override fun onUserVisible() {
         Log.i("HomeFragment","HomeFragment::onUserVisible")
     }
}