package com.inkbird.base.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.IntentSender;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static void removeFile(String filePath) {
        if (filePath == null || filePath.length() == 0) {
            return;
        }
        try {
            File file = new File(filePath);
            if (file.exists()) {
                removeFile(file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void removeFile(File file) {
        //如果是文件直接删除
        if (file.isFile()) {
            file.delete();
            return;
        }
        //如果是目录，递归判断，如果是空目录，直接删除，如果是文件，遍历删除
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                removeFile(f);
            }
            file.delete();
        }
    }

    public static void copyFile(String filePath, String newDirPath) {
        if (filePath == null || filePath.length() == 0) {
            return;
        }
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }
            //创建目标文件
            File newFile = new File(newDirPath);
            InputStream is = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(newFile);
            byte[] buffer = new byte[4096];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
            is.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moveFile(String filePath, String newDirPath) {
        if (filePath == null || filePath.length() == 0
                || newDirPath == null || newDirPath.length() == 0) {
            return;
        }
        try {
            //拷贝文件
            copyFile(filePath, newDirPath);
            //删除原文件
            removeFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void requestDeletePermission(Activity context, String path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Uri uri = ContentUris.withAppendedId( MediaStore.Images.Media.getContentUri("external"),getFilePathToMediaID(path,context));
            List<Uri> uriList = new ArrayList<>();
            uriList.add(uri);
            PendingIntent pendingIntent = MediaStore.createDeleteRequest(context.getContentResolver(), uriList);
            try {
                context.startIntentSenderForResult(pendingIntent.getIntentSender(), 1, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }else{
            File file = new File(path);
            if(file.exists()){
                file.delete();
            }
        }
    }

    public static long getFilePathToMediaID(String songPath, Context context) {
        long id = 0;
        ContentResolver cr = context.getContentResolver();

        Uri uri = MediaStore.Files.getContentUri("external");
        String selection = MediaStore.Audio.Media.DATA;
        String[] selectionArgs = {songPath};
        String[] projection = {MediaStore.Audio.Media._ID};
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor = cr.query(uri, projection, selection + "=?", selectionArgs, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                id = Long.parseLong(cursor.getString(idIndex));
            }
        }
        return id;
    }
}