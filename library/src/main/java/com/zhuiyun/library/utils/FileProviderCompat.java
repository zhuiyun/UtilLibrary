package com.inkbird.base.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import java.io.File;

public class FileProviderCompat {

    @NonNull
    public static Uri getUriForFile(@NonNull Context context, @NonNull File file) {
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = getUriForFile24(context, file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    @NonNull
    public static Uri getUriForFile24(@NonNull Context context, @NonNull File file) {
        return FileProvider.getUriForFile(context,
                context.getPackageName() + ".fileprovider",
                file);
    }


    public static void setDataAndType(@NonNull Intent intent, @NonNull Uri uri, @NonNull String type, boolean writeAble) {
        intent.setDataAndType(uri, type);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        }
    }
}