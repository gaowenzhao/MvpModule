package com.zhao.main

import android.support.v4.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.zhao.base.inf.BaseActivity
import com.zhao.base.inf.BaseFragment
import com.zhao.base.presenter.BasePresenter
import com.zhao.base.router.RouterUrl
import com.zhao.main.databinding.MainActivityBinding
class MainActivity : BaseActivity<MainActivityBinding, BasePresenter<*>>(){
    private val TAG = MainActivity::class.java.simpleName
    override var layoutId: Int = R.layout.main_activity
    //装fragment的实例集合
    private lateinit var  fragments: ArrayList<BaseFragment<*, *>>
    private var position = 0
    //缓存Fragment或上次显示的Fragment
    private var tempFragment: Fragment? = null
    override fun initView() {
        initFragment()
        initListener()
    }

    override fun initData() {
    }

    private fun initFragment() {
        //ARouter拿到多Fragment(这里需要通过ARouter获取，不能直接new,因为在组件独立运行时，宿主app是没有依赖其他组件，所以new不到其他组件的Fragment)
        val homeFragment = ARouter.getInstance().build(RouterUrl.Home.TabHome).navigation() as BaseFragment<*, *>
        val msgFragment = ARouter.getInstance().build(RouterUrl.Msg.TabMsg).navigation() as BaseFragment<*, *>
        val userFragment = ARouter.getInstance().build(RouterUrl.User.TabUser).navigation() as BaseFragment<*, *>
        fragments = ArrayList()
        fragments.add(homeFragment)
        fragments.add(msgFragment)
        fragments.add(userFragment)
        if (homeFragment != null) {
            //默认选中第一个
            tempFragment = homeFragment
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, homeFragment)
            homeFragment.userVisibleHint = true
            transaction.commitAllowingStateLoss()
        }
    }
    private fun initListener(){
        ui.rbHome.isChecked = true
        ui.rgMain.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.rb_home -> position = 0
                R.id.rb_msg -> position = 1
                R.id.rb_user -> position = 2
            }
            val baseFragment = getFragment(position)
            switchFragment(tempFragment, baseFragment)
        }
    }

    private fun getFragment(position: Int): BaseFragment<*, *>? {
        return if (fragments != null && fragments.size > 0) {
            fragments[position]
        } else null
    }
    private fun switchFragment(fragment: Fragment?, nextFragment: BaseFragment<*, *>?) {
        if (tempFragment !== nextFragment) {
            tempFragment = nextFragment!!
            if (nextFragment != null) {
                val transaction = supportFragmentManager.beginTransaction()
                //判断nextFragment是否添加成功
                if (!nextFragment.isAdded) {
                    //隐藏当前的Fragment
                    if (fragment != null) {
                        transaction.hide(fragment)
                        fragment.userVisibleHint = false
                    }
                    //添加Fragment
                    nextFragment.userVisibleHint = true
                    transaction.add(R.id.container, nextFragment).commitAllowingStateLoss()
                } else {
                    //隐藏当前Fragment
                    if (fragment != null) {
                        transaction.hide(fragment)
                        fragment.userVisibleHint = false
                    }
                    nextFragment.userVisibleHint = true
                    transaction.show(nextFragment).commitAllowingStateLoss()
                }
            }
        }
    }
}