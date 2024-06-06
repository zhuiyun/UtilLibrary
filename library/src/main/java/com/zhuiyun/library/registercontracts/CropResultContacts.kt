package com.inkbird.inkbirdapp.device.ibsm2sbt.view.registercontracts

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.activity.result.contract.ActivityResultContract
import java.io.File


/**
 * 裁剪图片
 */
class CropResultContacts : ActivityResultContract<CropImageResult, Uri>() {
    //裁剪后输出的图片文件Uri
    private var mUriOutput: Uri? = null

    override fun createIntent(context: Context, input: CropImageResult): Intent {
        //把CropImageResult转换成裁剪图片的意图

        val intent = Intent("com.android.camera.action.CROP")
        val mimeType = context.contentResolver.getType(
            input
                .uri
        )
        val imageName = System.currentTimeMillis().toString() +
                MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType) + ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues()
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
            values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
            mUriOutput = context.contentResolver
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        } else {
            mUriOutput = Uri.fromFile(File(context.externalCacheDir!!.absolutePath, imageName))
        }
        context.grantUriPermission(
            context.packageName,
            mUriOutput,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        //去除默认的人脸识别，否则和剪裁匡重叠
        intent.putExtra("noFaceDetection", true)
        intent.setDataAndType(input.uri, mimeType)
        //crop=true 有这句才能出来最后的裁剪页面.
        intent.putExtra("crop", "true")
        intent.putExtra("output", mUriOutput)
        //返回格式
        intent.putExtra("outputFormat", "JPEG")
        intent.putExtra("return-data", false)
        //配置裁剪图片的宽高比例
        if (input.aspectX != 0 && input.aspectY != 0) {
            intent.putExtra("aspectX", input.aspectX)
            intent.putExtra("aspectY", input.aspectY)
        }
        return intent
    }


    override fun parseResult(resultCode: Int, intent: Intent?): Uri {
        return mUriOutput!!
    }
}