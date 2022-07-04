package com.zhuiyun.library.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * @author: yun
 * @date: 2022/7/4 0004 10
 */
public class AppVersion {
    public static int getLocalVersion(Context ctx) {

        int localVersion = 0;

        try {

            PackageInfo packageInfo = ctx.getApplicationContext()

                    .getPackageManager()

                    .getPackageInfo(ctx.getPackageName(), 0);

            localVersion = packageInfo.versionCode;

            LogUtil.d("TAG", "本软件的版本。。" + localVersion);

        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();

        }

        return localVersion;

    }

    /**

     * 获取本地软件版本名称

     */

    public static String getLocalVersionName(Context ctx) {

        String localVersion = "";

        try {

            PackageInfo packageInfo = ctx.getApplicationContext()

                    .getPackageManager()

                    .getPackageInfo(ctx.getPackageName(), 0);

            localVersion = packageInfo.versionName;

            LogUtil.d("TAG", "本软件的版本。。" + localVersion);

        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();

        }

        return localVersion;
    }

    public void apkInfo(String absPath,Context context) {

        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = pm.getPackageArchiveInfo(absPath,PackageManager.GET_ACTIVITIES);
        if (pkgInfo != null) {
            ApplicationInfo appInfo = pkgInfo.applicationInfo;
            /* 必须加这两句，不然下面icon获取是default icon而不是应用包的icon */
            appInfo.sourceDir = absPath;
            appInfo.publicSourceDir = absPath;
            // 得到应用名
            String appName = pm.getApplicationLabel(appInfo).toString();
            // 得到包名
            String packageName = appInfo.packageName;
            // 得到版本信息
            String version = pkgInfo.versionName;
            /* icon1和icon2其实是一样的 */
            // 得到图标信息
            Drawable icon1 = pm.getApplicationIcon(appInfo);
            Drawable icon2 = appInfo.loadIcon(pm);
            String pkgInfoStr = String.format("PackageName:%s, Vesion: %s, AppName: %s", packageName, version, appName);

        }
    }
}
