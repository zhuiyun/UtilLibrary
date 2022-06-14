package com.zhuiyun.library.utils

import android.app.NotificationManager
import androidx.annotation.RequiresApi
import android.os.Build
import android.app.NotificationChannel
import android.content.Intent
import com.zhuiyun.library.utils.NotificationUtils
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.os.Vibrator
import android.media.RingtoneManager
import android.media.Ringtone
import android.net.Uri
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.HashMap

/**
 * 适配Android O
 */
object NotificationUtils {
    private var id = 0

    var tempHash = HashMap<String, Int>()

    private var notificationManager: NotificationManager? = null

    /**
     * 初始化
     *
     * @param context 引用全局上下文
     */
    fun init(context: Context) {
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    /**
     * 创建通知通道
     *
     * @param channelId   通道id
     * @param channelName 通道名称
     * @param importance  通道级别
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    fun createNotificationChannel(channelId: String?, channelName: String?, importance: Int) {
        val channel = NotificationChannel(channelId, channelName, importance)
        notificationManager!!.createNotificationChannel(channel)
    }

    /**
     * 发送通知
     *
     * @param channelId 通道id
     * @param title     标题
     * @param content   内容
     * @param intent    意图
     */
    fun sendNotification(context: Context,channelId: String?, title: String?, content: String, intent: Intent?) {
        val tempId: Int
        if (tempHash.containsKey(content)) {
            tempId = tempHash[content]!!
        } else {
            tempId = ++id
            tempHash[content] = tempId
        }
        var pendingIntent: PendingIntent? = null
        if (intent != null) {
            pendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }
        //震动
        val vibrator = context!!.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 180, 80, 120)
        vibrator.vibrate(vibrationPattern, -1)
        //播放声音
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val rt = RingtoneManager.getRingtone(context, uri)
        rt.play()
        val notification = NotificationCompat.Builder(context!!, channelId!!)
            .setContentTitle(title)
            .setContentText(content) //                .setSmallIcon(Android)
            .setWhen(System.currentTimeMillis())
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager!!.notify(tempId, notification)
    }

    fun isNotificationEnabled(context: Context?): Boolean {
        val notificationManagerCompat = NotificationManagerCompat
            .from(context!!)
        return notificationManagerCompat.areNotificationsEnabled()
    }

    /**
     * 打开通知设置页面
     */
    fun openNotification(context: Context) {
        val packageName = context.packageName
        val uid = context.applicationInfo.uid
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, uid)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            intent.putExtra("app_package", packageName)
            intent.putExtra("app_uid", uid)
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.data = Uri.parse("package:\$packageName")
        } else {
            intent.action = Settings.ACTION_SETTINGS
        }
        context.startActivity(intent)
    }

    /**
     * 发送通知
     *
     * @param channelId 通道id
     * @param title     标题
     * @param content   内容
     * @param intent    意图
     */
    fun sendNotification(context: Context,
        channelId: String?,
        title: String?,
        content: String,
        notifyId: Int,
        intent: Intent?
    ) {
        val tempId: Int
        if (tempHash.containsKey(content)) {
            tempId = tempHash[content]!!
        } else {
            tempId = ++id
            tempHash[content] = tempId
        }
        var pendingIntent: PendingIntent? = null
        if (intent != null) {
            pendingIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        }
        val vibrator = context!!.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 180, 80, 120)
        vibrator.vibrate(vibrationPattern, -1)
        val builder = NotificationCompat.Builder(context!!, channelId!!)
            .setContentTitle(title)
            .setContentText(content) //                .setSmallIcon(R.mipmap.ic_launcher)
            .setWhen(System.currentTimeMillis())
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        val bigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.setSummaryText(channelId).setBigContentTitle(title).bigText(content)
        builder.setStyle(bigTextStyle) //设置大文本样式
        val notification = builder.build()
        notificationManager!!.notify(tempId, notification)
    }

}