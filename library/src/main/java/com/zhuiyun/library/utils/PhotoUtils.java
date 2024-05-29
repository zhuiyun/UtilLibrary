package com.inkbird.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.inkbird.base.R;
import com.inkbird.base.http.RequestCode;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;

public class PhotoUtils {
    //图片裁剪的方法
    public static void startCropToFragment(Context mContext , Fragment fragment,Uri sourceUri, String fileName){
        try {
            File directory = new File(Environment.getExternalStoragePublicDirectory("Pictures").getPath());
            File cutfile = new File(directory, fileName + ".png");
            if (cutfile != null && cutfile.exists()) {
                cutfile.delete();
            }
            cutfile.createNewFile();
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;
            Uri outputUri;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                outputUri = FileProvider.getUriForFile(mContext.mContext, "com.inkbird.inkbirdapp.fileprovider", cutfile);
//            } else {
            outputUri = Uri.fromFile(cutfile);
            //}
            UCrop.Options options = new UCrop.Options();
            options.setStatusBarColor(mContext.getColor(R.color.normalBgDark));//设置状态栏颜色
            UCrop.of(sourceUri, outputUri)
                    .withAspectRatio(width - DisplayUtil.dp2px(mContext, 40), DisplayUtil.dp2px(mContext, 80))
                    .withMaxResultSize(width - DisplayUtil.dp2px(mContext, 40),DisplayUtil.dp2px(mContext, 80))
                    .withOptions(options)
                    .start(mContext, fragment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //图片裁剪的方法
    public static void startCropToActivity(Activity mContext , int requestCode,Uri sourceUri, String fileName){
        try {
            File directory = new File(mContext.getExternalFilesDir("images").getPath());
            File cutfile = new File(directory, fileName + ".png");
            if (cutfile.exists()) {
                boolean del = cutfile.delete();
            }
            boolean newFile = cutfile.createNewFile();
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;
            Uri outputUri;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                outputUri = FileProvider.getUriForFile(mContext.getContext(), "com.inkbird.inkbirdapp.fileprovider", cutfile);
//            } else {
            outputUri = Uri.fromFile(cutfile);
            //}
            UCrop.Options options = new UCrop.Options();
            options.setStatusBarColor(mContext.getColor(R.color.normalBgDark));//设置状态栏颜色
            UCrop.of(sourceUri, outputUri)
                    .withAspectRatio(width - DisplayUtil.dp2px(mContext, 40), DisplayUtil.dp2px(mContext, 80))
                    .withMaxResultSize(width - DisplayUtil.dp2px(mContext, 40),DisplayUtil.dp2px(mContext, 80))
                    .withOptions(options)
                    .start(mContext, requestCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //图片裁剪的方法
    public static void startCropToActivityPictures(Activity mContext , int requestCode,Uri sourceUri, String fileName){
        try {
            File directory = new File(Environment.getExternalStoragePublicDirectory("Pictures").getPath());
            File cutfile = new File(directory, fileName + ".png");
            if (cutfile.exists()) {
                boolean del = cutfile.delete();
            }
            boolean newFile = cutfile.createNewFile();
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;
            Uri outputUri;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                outputUri = FileProvider.getUriForFile(mContext.getContext(), "com.inkbird.inkbirdapp.fileprovider", cutfile);
//            } else {
            outputUri = Uri.fromFile(cutfile);
            //}
            UCrop.Options options = new UCrop.Options();
            options.setStatusBarColor(mContext.getColor(R.color.normalBgDark));//设置状态栏颜色
            UCrop.of(sourceUri, outputUri)
                    .withAspectRatio(width - DisplayUtil.dp2px(mContext, 40), DisplayUtil.dp2px(mContext, 80))
                    .withMaxResultSize(width - DisplayUtil.dp2px(mContext, 40),DisplayUtil.dp2px(mContext, 80))
                    .withOptions(options)
                    .start(mContext, requestCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Intent getAlbumIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        return intent;
    }
}
