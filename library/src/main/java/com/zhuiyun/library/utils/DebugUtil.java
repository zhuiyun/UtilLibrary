package com.zhuiyun.library.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * @author: yun
 * @date: 2022/7/4 0004 10
 */
public class DebugUtil {
    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
