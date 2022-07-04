package com.zhuiyun.library.utils;

import android.os.Build;
import android.util.Log;

import java.util.Map;

/**
 * @author: yun
 * @date: 2022/7/4 0004 10
 */
public class LogUtil {

    private static boolean DEBUG=true;


    public static void e(String tag, String text) {
        if(DEBUG){
            Log.e(tag, text);
        }
    }

    public static void i(String tag, String text) {
        if(DEBUG){
            Log.i(tag, text);
        }
    }

    public static void w(String tag, String text) {
        if(DEBUG){
            Log.w(tag, text);
        }
    }

    public static void d(String tag, String text) {
        if(DEBUG){
            Log.d(tag, text);
        }
    }

    /**
     * 打印请求的参数
     *
     * @param params
     */
    public static void showRequestParams(Map<String, String> params) {
        if(!DEBUG){
            return;
        }
        for (String key : params.keySet()) {
            LogUtil.i("key: " + key, "value: " + params.get(key));
        }
    }
}
