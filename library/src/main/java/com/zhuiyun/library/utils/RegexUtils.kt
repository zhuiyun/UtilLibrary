package com.zhuiyun.library.utils

import java.util.*

/**

 * @author: yun

 * @date: 2022/6/14 0014 10
 * 正则表达式

 */
object RegexUtils {
    /**
     * 每两个字节添加一个空格,去掉最后一个空格,转大写
     */
    fun getStringAddSpace(replace: String): String? {
        var replace = replace
        val regex = "(.{2})"
        replace = replace.replace(regex.toRegex(), "$1 ")
        return replace.uppercase(Locale.getDefault()).trim { it <= ' ' }
    }
}