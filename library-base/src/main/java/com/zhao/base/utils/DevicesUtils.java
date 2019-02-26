package com.zhao.base.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.zhao.base.app.BaseApplication;

/**
 * @author rg65544
 * @date 2018/6/12
 * 获取系统工具类
 * */
public class DevicesUtils {
    private static final String space = " ";
    public static String getDeviceBrands() {
        String model = android.os.Build.MODEL;
        return model;
    }

    /**
     * 获取手机品牌
     * */
    public static String getDeviceCarrier() {
        String carrier = android.os.Build.MANUFACTURER;
        return carrier;
    }

    public static String getImei() {
        // 没有权限，去申请权限，会弹出对话框
        TelephonyManager telephonyManager = (TelephonyManager) BaseApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }

    public static String getBuilder(String str){
        int len = str.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(str.charAt(i));
            if (i == 3 || i == 7 || i == 11 || i == 15) {
                if (i != len - 1) {
                    builder.append(space);
                }
            }
        }
        return builder.toString();
    }

}
