package com.zhao.base.utils

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.zhao.base.app.BaseApplication
/**
 * Created by hmh on 14/10/2017.
 */

class SPUtil {
    private val editor: SharedPreferences.Editor
    private val prefer: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.appContext)

    private constructor() {
        this.editor = this.prefer.edit()
    }

    companion object {
        private var instance: SPUtil? = null
        const val KEY_TOCKEN = "key_token"
        fun getInstance(): SPUtil {
            if (instance == null) {
                synchronized(SPUtil::class.java) {
                    if (instance == null) {
                        instance = SPUtil()
                    }
                }
            }
            return instance!!
        }
    }

    //存入sp
    fun put(name: String, data: Any) {
        when (data) {
            is String -> this.editor.putString(name, data)
            is Int -> this.editor.putInt(name, data)
            is Boolean -> this.editor.putBoolean(name, data)
            is Float -> this.editor.putFloat(name, data)
            is Long -> this.editor.putLong(name, data)
            else -> this.editor.putString(name, data.toString())
        }
        this.editor.apply()
    }

    //获取sp
    operator fun get(key: String, defaultObject: Any): Any? {
        when (defaultObject) {
            is String -> return this.prefer.getString(key, defaultObject)
            is Int -> return this.prefer.getInt(key, defaultObject)
            is Float -> return this.prefer.getFloat(key, defaultObject)
            is Boolean -> return this.prefer.getBoolean(key, defaultObject)
            is Long -> return this.prefer.getLong(key, defaultObject)
            else -> return null
        }
    }

    /**
     * 移除某个key值对应的值
     */
    fun remove(key: String) {
        this.editor.remove(key)
        this.editor.apply()
    }

    /**
     * 清除所有的内容
     */
    fun clear() {
        this.editor.clear()
        this.editor.apply()
    }

    /**
     * 查询某个key是否存在
     */
    operator fun contains(key: String): Boolean {
        return this.prefer.contains(key)
    }
}
