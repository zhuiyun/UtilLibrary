package com.zhuiyun.library.utils;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.HashMap;

/**
 * 适配Android O
 */
public class NotificationUtils {

    private static int id = 0;
    HashMap<String, Integer> tempHash = new HashMap<>();
    private Context context;
    private NotificationManager notificationManager;

    private NotificationUtils() {
    }

    public static NotificationUtils getInstance() {
        return NotificationUtilsHolder.notificationUtils;
    }

    /**
     * 初始化
     *
     * @param context 引用全局上下文
     */
    public void init(Context context) {
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
    }

    /**
     * 创建通知通道
     *
     * @param channelId   通道id
     * @param channelName 通道名称
     * @param importance  通道级别
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel(String channelId, String channelName, int importance) {

        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 发送通知
     *
     * @param channelId 通道id
     * @param title     标题
     * @param content   内容
     * @param intent    意图
     */
    public void sendNotification(String channelId, String title, String content, Intent intent) {
        int tempId;
        if (tempHash.containsKey(content)) {
            tempId = tempHash.get(content);
        } else {
            tempId = ++id;
            tempHash.put(content, tempId);
        }
        PendingIntent pendingIntent = null;
        if (intent != null) {
            pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        }
        //震动
        Vibrator vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        long[] vibrationPattern = new long[]{0, 180, 80, 120};
        vibrator.vibrate(vibrationPattern, -1);
        //播放声音
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone rt = RingtoneManager.getRingtone(context, uri);
        rt.play();


        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(content)
//                .setSmallIcon(Android)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        notificationManager.notify(tempId, notification);
    }

    public Boolean isNotificationEnabled(Context context) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat
                .from(context);
        return notificationManagerCompat.areNotificationsEnabled();
    }

    /**
     * 打开通知设置页面
     */
    public void openNotification(Context context) {
        String packageName = context.getPackageName();
        int uid = context.getApplicationInfo().uid;
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName);
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, uid);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", packageName);
            intent.putExtra("app_uid", uid);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:$packageName"));
        } else {
            intent.setAction(Settings.ACTION_SETTINGS);
        }
        context.startActivity(intent);


    }

    /**
     * 发送通知
     *
     * @param channelId 通道id
     * @param title     标题
     * @param content   内容
     * @param intent    意图
     */
    public void sendNotification(String channelId, String title, String content, int notifyId, Intent intent) {
        int tempId;
        if (tempHash.containsKey(content)) {
            tempId = tempHash.get(content);
        } else {
            tempId = ++id;
            tempHash.put(content, tempId);
        }
        PendingIntent pendingIntent = null;
        if (intent != null) {
            pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        }
        Vibrator vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        long[] vibrationPattern = new long[]{0, 180, 80, 120};

        vibrator.vibrate(vibrationPattern, -1);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(content)
//                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setSummaryText(channelId).setBigContentTitle(title).bigText(content);
        builder.setStyle(bigTextStyle); //设置大文本样式

        Notification notification = builder.build();
        notificationManager.notify(tempId, notification);
    }

    private static class NotificationUtilsHolder {
        public static final NotificationUtils notificationUtils = new NotificationUtils();
    }


}