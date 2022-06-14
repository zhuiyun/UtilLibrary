package com.zhuiyun.library.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.view.Gravity
import android.graphics.drawable.ColorDrawable
import com.zhuiyun.library.R

class ConfirmDialog(var mContext: Context) : Dialog(
    mContext
) {
    var resId = 0
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(resId)
    }

    override fun show() {
        super.show()
        val params = window!!.attributes
        params.gravity = Gravity.CENTER
        //        params.height= (int) (CommonUtils.getRealHeight(mContext)/3);
//        params.width=CommonUtils.getRealWidth(mContext)/3*2;
        window!!.decorView.setPadding(20, 0, 20, 0)
        window!!.attributes = params
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setWindowAnimations(R.style.dialogWindowAnimConfirm)
    }
}