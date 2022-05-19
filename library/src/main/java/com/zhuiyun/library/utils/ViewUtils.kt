package com.zhuiyun.library.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager

/**

 * @author: yun

 * @date: 2022/4/12 0012 13

 */
object ViewUtils {

    /**
     * Get Screen Real Height
     *
     * @param  Context
     * @return Real Height
     */
    fun getRealHeight(context: Context?): Int {
        val display: Display =
            getDisplay(context) ?: return 0
        val dm = DisplayMetrics()
        display.getRealMetrics(dm)
        return dm.heightPixels
    }


    /**
     * Get Display
     * @param   for get WindowManager
     * @return Display
     */
    private fun getDisplay(context: Context?): Display? {
        val wm: WindowManager?
        wm = if (context is Activity) {
            context.windowManager
        } else {
            context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        }
        return wm?.defaultDisplay
    }


    /**
     * Get Screen Real Width
     *
     * @param  Context
     * @return Real Width
     */
    fun getRealWidth(context: Context?): Int {
        val display: Display =
            getDisplay(context) ?: return 0
        val dm = DisplayMetrics()
        display.getRealMetrics(dm)
        return dm.widthPixels
    }
}