package com.zhuiyun.library.base

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

object CommonApplicationProxy : ApplicationProxy, ViewModelStoreOwner {

    lateinit var application: Application

    private val viewModelStore = ViewModelStore()

    override fun onCreate(application: Application) {
        CommonApplicationProxy.application = application
    }

    override fun onTerminate() {
        viewModelStore.clear()
    }

    override fun getViewModelStore() = viewModelStore
}