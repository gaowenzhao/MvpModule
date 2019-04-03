package com.zhao.base.inf

import android.databinding.ViewDataBinding
import com.zhao.base.presenter.BasePresenter

abstract class BaseSimpleFragment<V : ViewDataBinding> : BaseFragment<V,BasePresenter<*>>()