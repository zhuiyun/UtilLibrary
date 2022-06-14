package com.zhuiyun.library.utils

import com.zhuiyun.library.utils.NumberUtils
import java.lang.Exception

/**
 * @author: yun
 * @date: 2022/4/20 0020 09
 */
object NumberUtils {
    fun strToInt(str: String): Int {
        var result = 0
        result = try {
            strToDouble(str).toInt()
        } catch (e: Exception) {
            return result
        }
        return result
    }

    fun strToDouble(str: String): Double {
        var result = 0.0
        result = try {
            str.toDouble()
        } catch (e: Exception) {
            return result
        }
        return result
    }

    fun strToFloat(str: String): Float {
        var result = 0.0f
        result = try {
            str.toFloat()
        } catch (e: Exception) {
            return result
        }
        return result
    }
}