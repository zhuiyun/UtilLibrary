package com.zhuiyun.library.vmbase

import android.view.View

/**

 * @author: yun

 * @date: 2022/10/8 0008 10

 */
abstract class OnMultiClickListener: View.OnClickListener {

    private var mLastClickTime: Long = 0
    companion object{
        const val MIN_CLICK_TIME = 500
    }

    override fun onClick(v: View?) {
        var mCurClickTime =System.currentTimeMillis()

        if (mCurClickTime - mLastClickTime >= MIN_CLICK_TIME) {
            //超过点击间隔后再将lastClickTime重置为当前点击时间
            mLastClickTime = mCurClickTime
            onMultiClick(v)
        }
    }

    abstract fun onMultiClick(view: View?)
}