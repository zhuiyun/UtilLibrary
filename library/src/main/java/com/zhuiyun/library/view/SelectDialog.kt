package com.zhuiyun.library.view

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.zhuiyun.library.R

class SelectDialog(context: Context, titleText: String?, btn1Text: String?, btn2Text: String?) :
    Dialog(context) {
    var btn1: Button
    var btn2: Button
    var cancel: Button
    var title: TextView
    lateinit var selectListener: SelectListener


    private fun initClick() {
        cancel.setOnClickListener { view: View? -> dismiss() }
        btn1.setOnClickListener { view: View? ->
            if (selectListener != null) {
                selectListener!!.select(0)
            }
            dismiss()
        }
        btn2.setOnClickListener { view: View? ->
            if (selectListener != null) {
                selectListener!!.select(1)
            }
            dismiss()
        }
    }

    override fun show() {
        super.show()
        setWindowSize()
    }

    private fun setWindowSize() {
        // 为获取屏幕宽、高
        // 获取对话框当前的參数值
        val p = window!!.attributes
        p.height = ViewGroup.LayoutParams.WRAP_CONTENT
        p.width = ViewGroup.LayoutParams.MATCH_PARENT
        // 设置本身透明度
        p.alpha = 1.0f
        // 设置黑暗度
        p.dimAmount = 0.4f
        p.gravity = Gravity.BOTTOM
        window!!.attributes = p
        window!!.setWindowAnimations(R.style.showBottomDialog)
        setCanceledOnTouchOutside(true)
    }

    interface SelectListener {
        fun select(position: Int)
    }

    init {
        setContentView(R.layout.dialog_select)
        btn1 = findViewById(R.id.btn1)
        btn1.text = btn1Text
        btn2 = findViewById(R.id.btn2)
        btn2.text = btn2Text
        cancel = findViewById(R.id.btnCancel)
        title = findViewById(R.id.title)
        title.text = titleText
        initClick()
    }
}