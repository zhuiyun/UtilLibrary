package com.inkbird.inkbirdapp.base.widget.themeView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.inkbird.inkbirdapp.R;
import com.inkbird.base.sp.SharedPreferencesUtil;

@SuppressLint("AppCompatCustomView")
public class NightEditText extends EditText {
    public NightEditText(Context context) {
        super(context);
    }

    public NightEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttribute(context,attrs);
    }

    public NightEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute(context,attrs);
    }

    public void initAttribute(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.nightView);
        String deviceType = a.getString(R.styleable.nightView_deviceType);

        if (!TextUtils.isEmpty(deviceType)) {
            if (!deviceType.isEmpty()) {
                boolean isNight = SharedPreferencesUtil.getBoolean(context, "night_" + deviceType, false);
                if (isNight) {
                    setTextColor(getResources().getColor(R.color.normalWhite));
                    setHintTextColor(getResources().getColor(R.color.normalWhite));
                } else {
                    setTextColor(getResources().getColor(R.color.normalBlack));
                    setHintTextColor(getResources().getColor(R.color.normalBlack));
                }
            }
        }
    }

    public void changeTheme(boolean isDark) {
        if (isDark) {
            setTextColor(getResources().getColor(R.color.normalWhite));
            setHintTextColor(getResources().getColor(R.color.normalWhite));
        } else {
            setTextColor(getResources().getColor(R.color.normalBlack));
            setHintTextColor(getResources().getColor(R.color.normalBlack));
        }
    }
}