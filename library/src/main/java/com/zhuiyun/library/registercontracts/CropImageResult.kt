package com.inkbird.inkbirdapp.device.ibsm2sbt.view.registercontracts

import android.net.Uri


/**
 * 裁剪图片配置类
 */
data class CropImageResult(
    var uri: Uri, //裁剪框横向比例数值
    var aspectX: Int ,var aspectY: Int)