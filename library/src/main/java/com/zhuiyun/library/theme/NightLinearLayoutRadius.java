package com.inkbird.inkbirdapp.base.widget.themeView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.inkbird.inkbirdapp.R;
import com.inkbird.base.sp.SharedPreferencesUtil;

public class NightLinearLayoutRadius extends LinearLayout {
    Drawable bright;
    Drawable dark;

    public NightLinearLayoutRadius(Context context) {
        this(context, null);
    }

    public NightLinearLayoutRadius(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NightLinearLayoutRadius(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NightLinearLayoutRadius(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttribute(context, attrs);
    }

    public void initAttribute(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.nightView);
        String deviceType = a.getString(R.styleable.nightView_deviceType);
        bright = a.getDrawable(R.styleable.nightView_bright);
        dark = a.getDrawable(R.styleable.nightView_dark);
        if (!TextUtils.isEmpty(deviceType)) {
            if (!deviceType.isEmpty()) {
                boolean isNight = SharedPreferencesUtil.getBoolean(context, "night_" + deviceType, false);
                if (isNight) {
                    if (dark != null) {
                        setBackground(dark);
                    } else {
                        setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_item_dark, null));
                    }
                } else {
                    if (bright != null) {
                        setBackground(bright);
                    } else {
                        setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_item_bright, null));
                    }
                }
            }
        }
    }

    public void changeTheme(boolean isDark) {
        if (isDark) {
            if (dark != null) {
                setBackground(dark);
            } else {
                setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_item_dark, null));
            }
        } else {
            if (bright != null) {
                setBackground(bright);
            } else {
                setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.bg_item_bright, null));
            }
        }
    }
}