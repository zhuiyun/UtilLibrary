package com.zhuiyun.library.base

import android.app.Application

interface ApplicationProxy {

    fun onCreate(application: Application)

    fun onTerminate()

}