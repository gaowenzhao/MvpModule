package com.zhao.base.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhao.base.BuildConfig
import com.zhao.base.eventbus.EventBusUtil
import com.zhao.base.utils.StatusBarUtil

/**
 * Fragment父类
 */
abstract class BaseFragment<V : ViewDataBinding,T : BasePresenterI> : Fragment(){
    open var isEventbus = false
    open var mPresenter: T? = null
    var mRootView: View? = null
    lateinit var mContext: Context
    open var bundle: Bundle? = null
    private var isViewCreated: Boolean = false
//    private var isUIVisible: Boolean = false// release(模块)
    private var isUIVisible: Boolean = true//debug
    private var isFirstVisible:Boolean = false//是否已经加载过
    abstract var layoutId: Int
    abstract var darkMode: Boolean
    lateinit var ui: V
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (bundle != null) {
            outState.putBundle("bundle", bundle)
        }
    }
    /**
     * 绑定activity
     *
     * @param context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    /**
     * 运行在onAttach之后
     * 可以接受别人传递过来的参数,实例化对象.
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取bundle,并保存起来
        if (savedInstanceState != null) {
            bundle = savedInstanceState.getBundle("bundle")
        } else {
            bundle = if (arguments == null) Bundle() else arguments
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (null == mRootView) {
            ui = DataBindingUtil.inflate(inflater,layoutId,container,false)
            mRootView = ui.root
        } else {
            val parent = mRootView!!.parent as ViewGroup
            parent.removeView(mRootView)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (null == mRootView) {
            mRootView = view
        }
        isViewCreated = true
        lazyLoad() // 执行懒加载,因为无法确定setUserVisibleHint和onViewCreated哪个方法先执行，因此两个方法里面都需要调用lazyLoad
    }
    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            isUIVisible = true
            lazyLoad()
        } else {
            isUIVisible = false
            onUserInvisible()
        }
    }

    /**
     * 加载要显示的数据
     */
    private fun lazyLoad() {
        //需要进行双重判断，避免因为setUserVisibleHint先于onViewCreaetd调用时，出现空指针
        if (isViewCreated && isUIVisible) {//只会首次加载
            initViewsAndEvents()
            isFirstVisible = true
            isViewCreated = false
            isUIVisible = false
        }else{                      //每次界面可见都加载(除了首次加载)，加载过的界面才可以回调
            if(isFirstVisible){
                StatusBarUtil.setDarkMode(activity,darkMode)
                onUserVisible()
            }
        }
    }
    private fun initViewsAndEvents() {
        if (isEventbus) {
            EventBusUtil.register(this)
        }
        StatusBarUtil.setDarkMode(activity,darkMode)
        initView(mRootView!!)
        initData()
    }
    override fun onDestroy() {
        if (mPresenter != null) {
            mPresenter?.unsubcrible()
        }
        if (isEventbus) {
            EventBusUtil.unregister(this)
        }
        super.onDestroy()
    }

    override fun getContext(): Context {
        if (null == mContext) {
            mContext = activity!!
        }
        return mContext
    }
    protected abstract fun initData()
    protected abstract fun initView(RootView: View)
    protected open fun onUserVisible() {}
    protected open fun onUserInvisible() {}
}
