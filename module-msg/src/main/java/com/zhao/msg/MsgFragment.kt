package com.zhao.msg

import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhao.base.inf.BaseFragment
import com.zhao.base.presenter.BasePresenter
import com.zhao.base.utils.router.RouterUrl
import com.zhao.msg.databinding.FragmentMsgBinding
@Route(path = RouterUrl.Msg.TabMsg)
class MsgFragment : BaseFragment<FragmentMsgBinding, BasePresenter<*>>(){
    override var darkMode: Boolean = true
    override var fitsSystemWindows: Boolean = true
    override var layoutId: Int = R.layout.fragment_msg

    override fun initData() {
    }

    override fun initView(RootView: View) {

    }
    override fun onUserVisible() {
        Log.i("MsgFragment","MsgFragment::onUserVisible")
    }
}