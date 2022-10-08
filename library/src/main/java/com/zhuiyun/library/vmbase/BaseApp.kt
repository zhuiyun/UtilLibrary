package com.zhuiyun.library.vmbase

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**

 * @author: yun

 * @date: 2022/10/8 0008 10

 */
open abstract class BaseApp : Application() {

    companion object{
        var context: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }
}