package com.inkbird.inkbirdapp.device.ibsm2sbt.view.registercontracts

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import com.inkbird.base.utils.FileProviderCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class CameraResultContacts:ActivityResultContract<Any?, Uri>() {
    lateinit var uri:Uri
    override fun createIntent(context: Context, input: Any?): Intent {
        val directory = File(context.getExternalFilesDir("images")!!.path)
        val sdf = SimpleDateFormat("yyyyMMddHHmmss")
        val photoPathName = "inkbird" + sdf.format(Date(System.currentTimeMillis())) + ".png"
        val takePhotoTempFile = File(directory, photoPathName)
        val takePhotoTempFilePath = takePhotoTempFile.path
        val dir = File(
            takePhotoTempFilePath.substring(
                0,
                takePhotoTempFilePath.lastIndexOf(File.separator)
            )
        )
        if (!dir.exists()) dir.mkdirs()
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        uri = FileProviderCompat.getUriForFile(context, takePhotoTempFile)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        return cameraIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri {
        return uri
    }
}