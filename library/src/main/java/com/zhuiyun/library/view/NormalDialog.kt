package com.inkbird.inkbirdapp.device.ibsm2sbt.widget

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.inkbird.inkbirdapp.R

object NormalDialog {
    fun showNormalDialog(
        mContext: Context,
        onCancelClick: (dialog: Dialog) -> Unit,
        onComfirmClick: (dialog: Dialog) -> Unit
    ) {
        val dialog = Dialog(mContext, R.style.NormalDialog)
        val view = View.inflate(mContext, R.layout.dialog_ith21_deletedata, null)
        dialog.setContentView(view)

        dialog.findViewById<View>(R.id.cancel).setOnClickListener { v: View? ->
            onCancelClick(dialog)
        }
        dialog.findViewById<View>(R.id.confirm).setOnClickListener { v: View? ->
            onComfirmClick(dialog)
        }
        val window = dialog.window
        window?.decorView?.setPadding(20, 0, 20, 0)
        window?.setGravity(Gravity.CENTER)
        window?.setWindowAnimations(R.style.BottomDialogStyle)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }


    fun showNormalDialog(
        mContext: Context, title: String, tipContent: String,
        onCancelClick: (dialog: Dialog) -> Unit,
        onComfirmClick: (dialog: Dialog) -> Unit
    ) {
        val dialog = Dialog(mContext, R.style.NormalDialog)
        val view = View.inflate(mContext, R.layout.dialog_ith21_deletedata, null)
        dialog.setContentView(view)
        dialog.findViewById<TextView>(R.id.title).text = title
        dialog.findViewById<TextView>(R.id.notice).text = tipContent

        dialog.findViewById<View>(R.id.cancel).setOnClickListener { v: View? ->
            onCancelClick(dialog)
        }
        dialog.findViewById<View>(R.id.confirm).setOnClickListener { v: View? ->
            onComfirmClick(dialog)
        }
        val window = dialog.window
        window?.decorView?.setPadding(20, 0, 20, 0)
        window?.setGravity(Gravity.CENTER)
        window?.setWindowAnimations(R.style.BottomDialogStyle)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }

    fun showNormalDialog(
        mContext: Context, title: String, tipContent: String,
        onComfirmClick: (dialog: Dialog) -> Unit
    ) {
        val dialog = Dialog(mContext, R.style.NormalDialog)
        val view = View.inflate(mContext, R.layout.dialog_ith21_deletedata, null)
        dialog.setContentView(view)
        dialog.findViewById<TextView>(R.id.title).text = title
        dialog.findViewById<TextView>(R.id.notice).text = tipContent
        dialog.findViewById<View>(R.id.cancel).setOnClickListener { v: View? ->
            dialog.dismiss()
        }
        dialog.findViewById<View>(R.id.confirm).setOnClickListener { v: View? ->
            onComfirmClick(dialog)
        }
        val window = dialog.window
        window?.decorView?.setPadding(20, 0, 20, 0)
        window?.setGravity(Gravity.CENTER)
        window?.setWindowAnimations(R.style.BottomDialogStyle)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }
}