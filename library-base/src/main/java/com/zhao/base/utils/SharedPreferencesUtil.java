package com.zhao.base.utils;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.zhao.base.ui.BaseApplication;

/**
 * Created by hmh on 14/10/2017.
 */

public class SharedPreferencesUtil {
    private static SharedPreferencesUtil instance;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefer;

    public static final String KEY_USER_ID = "key_userId";
    public static final String KEY_TOCKEN = "key_token";
    public static final String KEY_MOBILE = "key_mobile";
    public static final String KEY_COOKIES = "key_cookies";
    public static final String KEY_FIRST_START = "key_firstStart";

    @SuppressLint("CommitPrefEdits")
    private SharedPreferencesUtil(){
        this.prefer = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getAppContext());
        this.editor = this.prefer.edit();
    }

    public static SharedPreferencesUtil getInstance(){
        if(instance == null){
            synchronized(SharedPreferencesUtil.class){
                if(instance == null){
                    instance = new SharedPreferencesUtil();
                }
            }
        }

        return instance;
    }

    //存入sp
    public void put(String name, Object data){
        if(data instanceof String){
            this.editor.putString(name, (String) data);
        }
        else if(data instanceof Integer){
            this.editor.putInt(name, (Integer) data);
        }
        else if(data instanceof Boolean){
            this.editor.putBoolean(name, (Boolean) data);
        }
        else if(data instanceof Float){
            this.editor.putFloat(name, (Float) data);
        }
        else if(data instanceof Long){
            this.editor.putLong(name, (Long) data);
        }
        else{
            this.editor.putString(name, data.toString());
        }

        this.editor.apply();
    }

    //获取sp
    public Object get(String key, Object defaultObject){
        if(defaultObject instanceof String){
            return this.prefer.getString(key, (String) defaultObject);
        }
        else if(defaultObject instanceof Integer){
            return this.prefer.getInt(key, (Integer) defaultObject);
        }
        else if(defaultObject instanceof Float){
            return this.prefer.getFloat(key, (Float) defaultObject);
        }
        else if(defaultObject instanceof Boolean){
            return this.prefer.getBoolean(key, (Boolean) defaultObject);
        }
        else if(defaultObject instanceof Long){
            return this.prefer.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 移除某个key值对应的值
     */
    public void remove(String key){
        this.editor.remove(key);
        this.editor.apply();
    }

    /**
     * 清除所有的内容
     */
    public void clear(){
        this.editor.clear();
        this.editor.apply();
    }

    /**
     * 查询某个key是否存在
     */
    public boolean contains(String key){
        return this.prefer.contains(key);
    }
}
