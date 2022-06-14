package com.zhuiyun.library.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.location.LocationManager
import android.telephony.TelephonyManager
import android.net.wifi.WifiManager
import android.net.wifi.WifiInfo
import androidx.core.app.ActivityCompat
import com.zhuiyun.library.utils.NetworkUtils
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException

/**
 * 作者: leiyuanxin
 * 时间: 2017/9/8 17:24
 * 邮箱: leiyuanxin@eims.com.cn
 * 描述：
 */
object NetworkUtils {
    /**
     * 网络是否可用
     *
     * @param context
     * @return
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivity = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity == null) {
        } else {
            val info = connectivity.allNetworkInfo
            if (info != null) {
                for (i in info.indices) {
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
        }
        return false
    }

    /**
     * Gps是否打开
     *
     * @param context
     * @return
     */
    fun isGpsEnabled(context: Context): Boolean {
        val locationManager = context
            .getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val accessibleProviders = locationManager.getProviders(true)
        return accessibleProviders != null && accessibleProviders.size > 0
    }

    /**
     * wifi是否打开
     */
    fun isWifiEnabled(context: Context): Boolean {
        val mgrConn = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mgrTel = context
            .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false
        }else{
            mgrConn.activeNetworkInfo != null && mgrConn
                .activeNetworkInfo!!.state == NetworkInfo.State.CONNECTED || mgrTel
                .networkType == TelephonyManager.NETWORK_TYPE_UMTS
        }

    }

    /**
     * 判断当前网络是否是wifi网络
     * if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) { //判断3G网
     *
     * @param context
     * @return boolean
     */
    fun isWifi(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return (activeNetInfo != null
                && activeNetInfo.type == ConnectivityManager.TYPE_WIFI)
    }

    /**
     * 判断当前网络是否是3G网络
     *
     * @param context
     * @return boolean
     */
    fun is3G(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return (activeNetInfo != null
                && activeNetInfo.type == ConnectivityManager.TYPE_MOBILE)
    }

    /**
     * 获取移动网IP
     * @param context
     * @return
     */
    fun getIPByMobile(context: Context?): String? {
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
    }

    /**
     * 根据Wifi网络获取IP地址
     * @param context
     * @return
     */
    fun getIPByWifi(context: Context): String {
        // 获取wifi服务
        val wifiManager =
            context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        // 判断wifi是否开启
        if (!wifiManager.isWifiEnabled) {
            wifiManager.isWifiEnabled = true
        }
        val wifiInfo = wifiManager.connectionInfo
        val i = wifiInfo.ipAddress
        return (i and 0xFF).toString() + "." + (i shr 8 and 0xFF) + "." + (i shr 16 and 0xFF) + "." + (i shr 24 and 0xFF)
    }

    /**
     * 获取手机当前IP地址
     * @return
     */
    fun getIpAddress(context: Context): String? {
        if (!isNetworkAvailable(context)) {
            return ""
        }
        return if (is3G(context)) {

            //获取移动网(3大运营商提供的IP)
            getIPByMobile(context)
        } else {

            //获取Wifi网络IP
            getIPByWifi(context)
        }
    }
}