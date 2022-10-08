package com.zhuiyun.library.vmbase

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**

 * @author: yun

 * @date: 2022/10/8 0008 10

 */
open class ViewBindingHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root) {

}