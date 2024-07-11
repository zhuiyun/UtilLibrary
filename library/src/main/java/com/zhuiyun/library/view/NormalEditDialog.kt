package com.inkbird.inkbirdapp.device.ibsm2sbt.widget

import android.app.Dialog
import android.content.Context
import android.text.InputFilter
import android.text.TextUtils
import android.text.method.DigitsKeyListener
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.inkbird.inkbirdapp.R
import com.inkbird.inkbirdapp.device.ith11.utils.LengthFilter

object NormalEditDialog {
    fun showDecimalNumberEditDialog(
        context: Context,
        title: String = "",
        tips: String = "",
        hint: String = "",
        onComfirmClick: (inputString: String) -> Unit
    ) {
        val dialog = Dialog(context, R.style.EditTextDialog)
        val view = View.inflate(context, R.layout.dialog_edittext_normal, null)
        dialog.setContentView(view)
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val layoutParams: WindowManager.LayoutParams = dialog.window!!.attributes
        layoutParams.width = (width * 0.8).toInt()
        dialog.window?.setAttributes(layoutParams)
        dialog.show()
        if (!TextUtils.isEmpty(title)) {
            dialog.findViewById<TextView>(R.id.title).text = title
        }
        if (!TextUtils.isEmpty(tips)) {
            dialog.findViewById<TextView>(R.id.tv_tip).text = tips
        }
        val editText = dialog.findViewById<EditText>(R.id.et_compensation_value)
        editText.filters = arrayOf<InputFilter>(LengthFilter(1))
        editText.requestFocus()
        editText.keyListener = DigitsKeyListener.getInstance("0123456789-.")
        if (!TextUtils.isEmpty(hint)) {
            editText.hint = hint
        }
        dialog.findViewById<TextView>(R.id.tv_compensation_confirm).setOnClickListener {
            dialog.dismiss()
            val text = editText.text.toString()
            onComfirmClick(text)
        }
    }

    fun showNumberEditDialog(
        context: Context,
        title: String = "",
        tips: String = "",
        hint: String = "",
        onComfirmClick: (inputString: String) -> Unit
    ) {
        val dialog = Dialog(context, R.style.EditTextDialog)
        val view = View.inflate(context, R.layout.dialog_edittext_normal, null)
        dialog.setContentView(view)
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val layoutParams: WindowManager.LayoutParams = dialog.window!!.attributes
        layoutParams.width = (width * 0.8).toInt()
        dialog.window?.setAttributes(layoutParams)
        dialog.show()
        if (!TextUtils.isEmpty(title)) {
            dialog.findViewById<TextView>(R.id.title).text = title
        }
        if (!TextUtils.isEmpty(tips)) {
            dialog.findViewById<TextView>(R.id.tv_tip).text = tips
        }
        val editText = dialog.findViewById<EditText>(R.id.et_compensation_value)
        editText.filters = arrayOf<InputFilter>(LengthFilter(1))
        editText.requestFocus()
        editText.keyListener = DigitsKeyListener.getInstance("0123456789-")
        if (!TextUtils.isEmpty(hint)) {
            editText.hint = hint
        }
        dialog.findViewById<TextView>(R.id.tv_compensation_confirm).setOnClickListener {
            dialog.dismiss()
            val text = editText.text.toString()
            onComfirmClick(text)
        }
    }

    fun showEditDialog(
        context: Context,
        title: String = "",
        tips: String = "",
        hint: String = "",
        onComfirmClick: (inputString: String) -> Unit
    ) {
        val dialog = Dialog(context, R.style.EditTextDialog)
        val view = View.inflate(context, R.layout.dialog_edittext_normal, null)
        dialog.setContentView(view)
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val layoutParams: WindowManager.LayoutParams = dialog.window!!.attributes
        layoutParams.width = (width * 0.8).toInt()
        dialog.window?.setAttributes(layoutParams)
        dialog.show()
        if (!TextUtils.isEmpty(title)) {
            dialog.findViewById<TextView>(R.id.title).text = title
        }
        if (!TextUtils.isEmpty(tips)) {
            dialog.findViewById<TextView>(R.id.tv_tip).text = tips
        }
        val editText = dialog.findViewById<EditText>(R.id.et_compensation_value)
        editText.requestFocus()
        if (!TextUtils.isEmpty(hint)) {
            editText.hint = hint
        }
        dialog.findViewById<TextView>(R.id.tv_compensation_confirm).setOnClickListener {
            dialog.dismiss()
            val text = editText.text.toString()
            onComfirmClick(text)
        }
    }
}