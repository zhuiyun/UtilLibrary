package com.zhuiyun.library.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.zhuiyun.library.R

/**

 * @author: yun

 * @date: 2022/4/14 0014 11

 */
class LoadingDialog(context: Context) : Dialog(context) {
     lateinit var imageView:ImageView
    lateinit var  animation:Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        imageView=findViewById(R.id.image)
        setCanceledOnTouchOutside(false)
       animation = AnimationUtils.loadAnimation(
            window!!.context, R.anim.iv_rotate
        )
        val lin = LinearInterpolator()
        animation.interpolator = lin
        imageView.startAnimation(animation)
    }


    override fun show() {
        super.show()
        val params = window!!.attributes
        params.gravity = Gravity.CENTER
        window!!.decorView.setPadding(20, 0, 20, 0)
        window!!.attributes = params
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window!!.setWindowAnimations(R.style.showloadingDialog)
    }

    override fun onStart() {
        super.onStart()
        imageView.startAnimation(animation)
    }

    override fun onStop() {
        super.onStop()
        imageView.clearAnimation()

    }

    interface OnCancelListener {
        fun clickCancel()
    }
}