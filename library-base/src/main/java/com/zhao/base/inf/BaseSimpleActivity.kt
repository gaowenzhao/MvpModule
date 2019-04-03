package com.zhao.base.inf

import android.databinding.ViewDataBinding
import com.zhao.base.presenter.BasePresenter

abstract class BaseSimpleActivity<V : ViewDataBinding> : BaseActivity<V,BasePresenter<*>>()