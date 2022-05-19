package com.zhuiyun.library.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.zhuiyun.library.R;


public class SelectDialog extends Dialog {
    Button btn1, btn2, cancel;
    TextView title;
    SelectListener selectListener;

    public SelectDialog(@NonNull Context context, String titleText, String btn1Text, String btn2Text) {
        super(context);
        setContentView(R.layout.dialog_select);
        btn1 = findViewById(R.id.btn1);
        btn1.setText(btn1Text);
        btn2 = findViewById(R.id.btn2);
        btn2.setText(btn2Text);
        cancel = findViewById(R.id.btnCancel);
        title = findViewById(R.id.title);
        title.setText(titleText);
        initClick();
    }

    public void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }

    private void initClick() {

        cancel.setOnClickListener(view -> dismiss());
        btn1.setOnClickListener(view -> {
            if (selectListener != null) {
                selectListener.select(0);
            }
            dismiss();
        });

        btn2.setOnClickListener(view -> {
            if (selectListener != null) {
                selectListener.select(1);
            }
            dismiss();
        });
    }

    @Override
    public void show() {
        super.show();
        setWindowSize();
    }

    private void setWindowSize() {
        // 为获取屏幕宽、高
        // 获取对话框当前的參数值
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        p.width = ViewGroup.LayoutParams.MATCH_PARENT;
        // 设置本身透明度
        p.alpha = 1.0f;
        // 设置黑暗度
        p.dimAmount = 0.4f;
        p.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(p);
        getWindow().setWindowAnimations(R.style.showBottomDialog);
        setCanceledOnTouchOutside(true);
    }

    public interface SelectListener {
        void select(int position);
    }

}
