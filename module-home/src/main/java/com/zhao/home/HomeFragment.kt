package com.zhao.home

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhao.base.ui.BaseFragment
import com.zhao.base.router.RouterUrl
import com.zhao.home.databinding.FragmentHomeBinding
import com.zhao.home.presenter.HomeContract
import com.zhao.home.presenter.HomePresenter


@Route(path = RouterUrl.Home.TabHome)
class HomeFragment : BaseFragment<FragmentHomeBinding, HomePresenter>(),HomeContract.View{
     override var mPresenter: HomePresenter? = HomePresenter()
     override var darkMode: Boolean = false
     override var layoutId: Int = R.layout.fragment_home
     override fun initData() {
          mPresenter?.getBannerList()
     }
     override fun initView(RootView: View) {
          mPresenter?.attachView(this, context!!)
          ui.toolbar.title = ""

     }
     override fun onBannerSuccess() {

     }
}