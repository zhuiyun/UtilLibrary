package com.inkbird.inkbirdapp.base.widget.themeView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.inkbird.inkbirdapp.R;
import com.inkbird.base.sp.SharedPreferencesUtil;

@SuppressLint("AppCompatCustomView")
public class NightTextView extends TextView {
    int bright;
    int dark;

    Drawable backgroundLightRes;
    Drawable backgroundDarkRes;

    public NightTextView(Context context) {
        this(context, null);
    }

    public NightTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NightTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NightTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttribute(context, attrs);
    }

    public void initAttribute(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.nightView);
        String deviceType = a.getString(R.styleable.nightView_deviceType);
        bright = a.getResourceId(R.styleable.nightView_bright, 0);
        dark = a.getResourceId(R.styleable.nightView_dark, 0);
        backgroundLightRes = a.getDrawable(R.styleable.nightView_backgroundLight);
        backgroundDarkRes = a.getDrawable(R.styleable.nightView_backgroundDark);

        if (!TextUtils.isEmpty(deviceType)) {
            if (!deviceType.equals("")) {
                boolean isNight = SharedPreferencesUtil.getBoolean(context, "night_" + deviceType, false);
                if (isNight) {
                    if (dark != 0) {
                        setTextColor(getResources().getColor(dark));
                    } else {
                        setTextColor(getResources().getColor(R.color.normalWhite));
                    }
                } else {
                    if (bright != 0) {
                        setTextColor(getResources().getColor(bright));
                    } else {
                        setTextColor(getResources().getColor(R.color.normalBlack));
                    }
                }

                if (isNight) {
                    if (backgroundDarkRes != null) {
                        setBackground(backgroundDarkRes);
                    } else {
                        setBackgroundResource(R.color.transparent);
                    }
                } else {
                    if (backgroundLightRes != null) {
                        setBackground(backgroundLightRes);
                    } else {
                        setBackgroundResource(R.color.transparent);
                    }
                }
            }
        }
    }

    public void changeTheme(boolean isDark) {
        if (isDark) {
            if (dark != 0) {
                setTextColor(getResources().getColor(dark));
            } else {
                setTextColor(getResources().getColor(R.color.normalWhite));
            }
        } else {
            if (bright != 0) {
                setTextColor(getResources().getColor(bright));
            } else {
                setTextColor(getResources().getColor(R.color.normalBlack));
            }
        }

        if (isDark) {
            if (backgroundDarkRes != null) {
                setBackground(backgroundDarkRes);
            } else {
                setBackgroundResource(R.color.transparent);
            }
        } else {
            if (backgroundLightRes != null) {
                setBackground(backgroundLightRes);
            } else {
                setBackgroundResource(R.color.transparent);
            }
        }
    }
}