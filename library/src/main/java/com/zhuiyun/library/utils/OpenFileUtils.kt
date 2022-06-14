package com.zhuiyun.library.utils

import android.content.Intent
import android.os.Build
import androidx.core.content.FileProvider
import com.zhuiyun.library.utils.OpenFileUtils
import android.text.TextUtils
import android.app.Activity
import android.content.Context
import android.net.Uri
import java.io.File
import java.util.*

/**
 * @author: yun
 * @date: 2022/5/19 0019 15
 */
object OpenFileUtils {
    fun openFile(context: Context, file: File) {
        val intent = Intent(Intent.ACTION_VIEW)
        //intent.addCategory(Intent.CATEGORY_DEFAULT);
        val uriForFile: Uri
        if (Build.VERSION.SDK_INT > 23) {
            //Android 7.0之后
            uriForFile = FileProvider.getUriForFile(context, "com.kingsong.tune.provider", file)
            //            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);//给目标文件临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else {
            uriForFile = Uri.fromFile(file)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //系统会检查当前所有已创建的Task中是否有该要启动的Activity的Task;
        intent.action = Intent.ACTION_VIEW
        // 若有，则在该Task上创建Activity；若没有则新建具有该Activity属性的Task，并在该新建的Task上创建Activity。
        intent.setDataAndType(uriForFile, getMimeTypeFromFile(file))
        context.startActivity(intent)
    }

    /**
     * 使用自定义方法获得文件的MIME类型
     */
    private fun getMimeTypeFromFile(file: File): String {
        var type = "*/*"
        val fName = file.name
        //获取后缀名前的分隔符"."在fName中的位置。
        val dotIndex = fName.lastIndexOf(".")
        if (dotIndex > 0) {
            //获取文件的后缀名
            val end = fName.substring(dotIndex).toLowerCase(Locale.getDefault())
            //在MIME和文件类型的匹配表中找到对应的MIME类型。
            val map: HashMap<String, String> = MyminmeMap.minmeMap
            if (!TextUtils.isEmpty(end) && map.containsKey(end)) {
                type = map[end].toString()
            }
        }
        return type
    }

    fun sendFileToWx(mActivity: Activity, file: File?) {
        val wechatIntent = Intent(Intent.ACTION_SEND)
        val uriForFile: Uri
        if (Build.VERSION.SDK_INT > 23) {
            //Android 7.0之后
            uriForFile = FileProvider.getUriForFile(mActivity, "com.kingsong.tune.provider", file!!)
            wechatIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else {
            uriForFile = Uri.fromFile(file)
        }
        wechatIntent.setPackage("com.tencent.mm")
        wechatIntent.type = "application/vnd.ms-excel"
        wechatIntent.putExtra(Intent.EXTRA_STREAM, uriForFile)
        mActivity.startActivity(wechatIntent)
    }
}