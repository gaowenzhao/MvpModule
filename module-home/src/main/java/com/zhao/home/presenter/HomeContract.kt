package com.zhao.home.presenter

/**
 * 首页
 */
interface HomeContract {

    /**
     * 界面的展示
     */
    interface View{
        fun onBannerSuccess()
    }

    /**
     * 接口请求
     */
    interface Presenter {
        /**
         * 首页Banner
         */
        fun getBannerList()
    }

}
