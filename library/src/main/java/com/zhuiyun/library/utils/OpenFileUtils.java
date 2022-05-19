package com.zhuiyun.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

/**
 * @author: yun
 * @date: 2022/5/19 0019 15
 */
public class OpenFileUtils {

    public static void openFile(Context context, File file){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uriForFile;
        if (Build.VERSION.SDK_INT > 23){
            //Android 7.0之后
            uriForFile = FileProvider.getUriForFile(context, "com.kingsong.tune.provider", file);
//            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);//给目标文件临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }else {
            uriForFile = Uri.fromFile(file);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//系统会检查当前所有已创建的Task中是否有该要启动的Activity的Task;
        intent.setAction(Intent.ACTION_VIEW);
        // 若有，则在该Task上创建Activity；若没有则新建具有该Activity属性的Task，并在该新建的Task上创建Activity。
        intent.setDataAndType(uriForFile,getMimeTypeFromFile(file));
        context.startActivity(intent);

    }

    /**
     * 使用自定义方法获得文件的MIME类型
     */
    private static String getMimeTypeFromFile(File file) {
        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex > 0) {
            //获取文件的后缀名
            String end = fName.substring(dotIndex).toLowerCase(Locale.getDefault());
            //在MIME和文件类型的匹配表中找到对应的MIME类型。
            HashMap<String, String> map = MymimeMap.getMimeMap();
            if (!TextUtils.isEmpty(end) && map.containsKey(end)) {
                type = map.get(end);
            }
        }
        return type;
    }

    public static void sendFileToWx(Activity mActivity, File file){
        Intent wechatIntent = new Intent(Intent.ACTION_SEND);
        Uri uriForFile;
        if (Build.VERSION.SDK_INT > 23) {
            //Android 7.0之后
            uriForFile = FileProvider.getUriForFile(mActivity, "com.kingsong.tune.provider", file);
            wechatIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            uriForFile = Uri.fromFile(file);
        }
        wechatIntent.setPackage("com.tencent.mm");
        wechatIntent.setType("application/vnd.ms-excel");
        wechatIntent.putExtra(Intent.EXTRA_STREAM, uriForFile);
        mActivity.startActivity(wechatIntent);

    }
}

