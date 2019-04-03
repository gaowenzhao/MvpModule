package com.zhao.base.utils

import com.zhao.base.BuildConfig

/**
 * @author rg65544
 * @date 2018/6/8
 * header头部的通用数据
 */
class HeaderUtils {
    companion object {

        /**
         * 返回IMEI号
         */
        @JvmStatic
        fun getIMEI(): String {
            return DevicesUtils.getImei()
        }

        /**
         * 返回android的appkey
         */
        @JvmStatic
        fun getAppKey(): String {
            return "haoyijie"
        }

        @JvmStatic
        fun getPlatform(): String {
            return "1"
        }

        /***获取版本号*/
        @JvmStatic
        fun getVersionName(): String {
            return BuildConfig.VERSION_NAME //待修改
        }

        @JvmStatic
        fun getToken(): String {
            val token: String = SPUtil.getInstance().get(SPUtil.KEY_TOCKEN, "") as String
//            return TestData.token
            return token
        }
    }
}
