package com.zhao.base.http.bean

import com.zhao.base.http.bean.BaseBean.Code.success
import com.zhao.base.http.bean.BaseBean.Code.unknown

 class BaseBean<T : Any> {

    var code: Int = unknown
    var message: String = ""
    var data: T?= null
    fun isSuccess(): Boolean {
        return success == code
    }

    object Code {
        const val unknown = -1
        const val success = 0
    }
}