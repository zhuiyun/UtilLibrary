package com.zhuiyun.library.vmbase

import android.view.View

/**

 * @author: yun

 * @date: 2022/10/8 0008 10

 */
interface ItemClickListener {
    fun onItemClicked(v: View?, position: Int)
}