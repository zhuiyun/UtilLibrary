package com.inkbird.base.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.List;

public class CommonUtils {

    public static int getStatusBarHeight(Context context) { // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    //判断wifi是否可用
    public static boolean isWifiEnabled(@NonNull Context context) {
        WifiManager wifiMgr = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiMgr.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
            ConnectivityManager connManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = connManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            return wifiInfo.isConnected();
        } else {
            return false;
        }
    }

    //判断网络是否连接
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    //判断2.4Gwifi是否可用
    public static boolean isWifi24G(@NonNull Context context) {
        int freq = 0;
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.LOLLIPOP) {
            freq = wifiInfo.getFrequency();
        } else {
            String ssid = wifiInfo.getSSID();
            if (ssid != null && ssid.length() > 2) {
                String ssidTemp = ssid.substring(1, ssid.length() - 1);
                List<ScanResult> scanResults = wifiManager.getScanResults();
                for (ScanResult scanResult : scanResults) {
                    if (scanResult.SSID.equals(ssidTemp)) {
                        freq = scanResult.frequency;
                        break;
                    }
                }
            }
        }
        return freq > 2400 && freq < 2500;
    }


    /**
     * 获取手机ip地址
     *
     * @param context
     * @return
     */
    public static String getLocalIPAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return Formatter.formatIpAddress(wifiInfo.getIpAddress());
    }

    public static List<BluetoothGattService> gattlist;

    public static void displayGattServices(@NonNull List<BluetoothGattService> gattServices) {
        gattlist = gattServices;
    }

    public static boolean isLocServiceEnable(@NonNull Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }

    public static boolean isAppForeground(@NonNull Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.processName.equals(context.getPackageName())) {
                    return appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
                }
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isTopActivity(Activity activity) {
        ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
        if (runningTasks != null && !runningTasks.isEmpty()) {
            String name = runningTasks.get(0).topActivity.getClassName();
            return name.contains(activity.getLocalClassName());
        }
        return false;
    }

    public static boolean isTopActivity(Context context, Class activity) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(1);
        if (runningTasks != null && !runningTasks.isEmpty()) {
            String name = runningTasks.get(0).topActivity.getClassName();
            return name.contains(activity.getName());
        }
        return false;
    }

    //判断Activity是否Destroy
    public static boolean isDestroy(@NonNull Activity activity) {
        return activity.isFinishing() || activity.isDestroyed();
    }

    @NonNull
    public static Bitmap decodeBitmapResource(@NonNull Resources resources, int id) {
        Bitmap bitmap;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        bitmap = BitmapFactory.decodeResource(resources, id, opts);
        return bitmap;
    }

    public static int getMonthLastDay(long mills) {
        Calendar a = Calendar.getInstance();
        a.setTimeInMillis(mills);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    public static int getMonthLastWeek(long mills) {
        Calendar a = Calendar.getInstance();
        a.setTimeInMillis(mills);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.WEEK_OF_MONTH);
    }

    public static int getWeekNumDay(int y, int m, int d) {
        int c = y / 100 + 1;
        if (m == 1) {
            m = 13;
            y = y - 1;
        } else if (m == 2) {
            m = 14;
            y = y - 1;
        }
        int tempDate = (y + (y / 4) + (c / 4) - 2 * c + (26 * (m + 1) / 10) + d - 1) % 7;
        if (tempDate < 0) {
            return 7 + tempDate;
        }
        return tempDate + 2;
    }

    public static int getMonthOfDay(int year, int month) {
        int day;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            day = 29;
        } else {
            day = 28;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return day;

        }
        return 0;
    }



    public static int dpToPx(@NonNull Context context, double dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f * (dp >= 0 ? 1 : -1));
    }

    public static int dip2px(@NonNull Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}