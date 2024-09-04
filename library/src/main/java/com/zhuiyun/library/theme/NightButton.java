package com.inkbird.inkbirdapp.base.widget.themeView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.inkbird.inkbirdapp.R;
import com.inkbird.base.sp.SharedPreferencesUtil;

@SuppressLint("AppCompatCustomView")
public class NightButton extends Button {
    public NightButton(Context context) {
        this(context, null);
    }

    public NightButton(Context context, @Nullable AttributeSet attrs) {
       this(context, attrs, 0);
    }

    public NightButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NightButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttribute(context, attrs);
    }

    public void initAttribute(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.nightView);
        String deviceType = a.getString(R.styleable.nightView_deviceType);

        if (!TextUtils.isEmpty(deviceType)) {
            if (deviceType != null && !deviceType.equals("")) {
                boolean isNight = SharedPreferencesUtil.getBoolean(context, "night_" + deviceType, false);
                if (isNight) {
                    setTextColor(getResources().getColor(R.color.normalWhite));
                } else {
                    setTextColor(getResources().getColor(R.color.normalBlack));
                }
            }
        }
    }

    public void changeTheme(boolean isDark) {
        if (isDark) {
            setTextColor(getResources().getColor(R.color.normalWhite));
        } else {
            setTextColor(getResources().getColor(R.color.normalBlack));
        }
    }
}