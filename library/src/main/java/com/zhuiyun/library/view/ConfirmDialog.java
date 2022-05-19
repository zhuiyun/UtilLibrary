package com.zhuiyun.library.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import com.zhuiyun.library.R;

public class ConfirmDialog extends Dialog {

    Context mContext;
    int resId;
    public ConfirmDialog(@NonNull Context context) {
        super(context);
        mContext=context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(resId);
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
//        params.height= (int) (CommonUtils.getRealHeight(mContext)/3);
//        params.width=CommonUtils.getRealWidth(mContext)/3*2;
        getWindow().getDecorView().setPadding(20,0,20,0);
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setWindowAnimations(R.style.dialogWindowAnimConfirm);
    }
}
