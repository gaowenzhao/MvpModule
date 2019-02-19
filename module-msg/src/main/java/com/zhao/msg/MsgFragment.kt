package com.zhao.msg

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhao.base.BaseFragment
import com.zhao.base.BasePresenter
import com.zhao.base.router.RouterUrl
import com.zhao.base.utils.StatusBarUtil
import com.zhao.msg.databinding.FragmentMsgBinding
@Route(path = RouterUrl.Msg.TabMsg)
class MsgFragment :BaseFragment<FragmentMsgBinding,BasePresenter<*>>(){
    override var darkMode: Boolean = true
    override var layoutId: Int = R.layout.fragment_msg

    override fun initData() {
    }

    override fun initView(RootView: View) {

    }
}