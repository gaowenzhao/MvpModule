package com.zhao.base.inf

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.zhao.base.utils.eventbus.EventBusUtil

/**
 * BaseActivity父类
 */
abstract class BaseActivity<V : ViewDataBinding,T : BasePresenterI> : AppCompatActivity(){
    open var isEventbus = false
    open var mPresenter: T? = null
    lateinit var context: Context
    abstract var layoutId: Int
    lateinit var ui: V
    //================ ===================
    //================ ===================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        ui = DataBindingUtil.setContentView(this,layoutId)
        if (isEventbus) {
            EventBusUtil.register(this)
        }
        ImmersionBar.with(this).init()
        initView()
        initData()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
    /**
     * 设置initView
     */
    protected abstract fun initView()
    protected abstract fun initData()


    override fun onDestroy() {
        if (mPresenter != null) {
            mPresenter?.unsubcrible()
        }
        if (isEventbus) {
            EventBusUtil.unregister(this)
        }
        super.onDestroy()
    }


}
