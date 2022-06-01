package com.zhuiyun.library.utils

import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.Service
import android.content.Context

/**

 * @author: yun

 * @date: 2022/6/1 0001 11

 */
object SystemUtils {
    //判断应用是否处于前台
    fun isAppRunningForeground(context: Context): Boolean {
        val activityManager = context.getSystemService(Service.ACTIVITY_SERVICE) as ActivityManager
        val runningAppProcessInfoList = activityManager.runningAppProcesses ?: return false
        for (processInfo in runningAppProcessInfoList) {
            if (processInfo.processName == context.packageName && processInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true
            }
        }
        return false
    }
}