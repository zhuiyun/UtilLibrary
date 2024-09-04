package com.inkbird.inkbirdapp.base.widget.themeView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.inkbird.inkbirdapp.R;
import com.inkbird.base.sp.SharedPreferencesUtil;

@SuppressLint("AppCompatCustomView")
public class NightImageView extends ImageView {
    Drawable bright;
    Drawable dark;

    public NightImageView(Context context) {
        this(context, null);
    }

    public NightImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NightImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NightImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
                if (bright != null && dark != null) {
                    if (isNight) {
                        setBackground(dark);
//                        setColorFilter(getResources().getColor(R.color.normalWhite));
                    } else {
                        setBackground(bright);
//                        setColorFilter(Color.parseColor("#3D474D"));
                    }
                } else {
                    if (isNight) {
                        setColorFilter(getResources().getColor(R.color.normalWhite));
                    } else {
                        setColorFilter(Color.parseColor("#3D474D"));
                    }
                }
            }
        }
    }

    public void changeTheme(boolean isDark) {
        if (bright != null && dark != null) {
            if (isDark) {
                setBackground(dark);
//                        setColorFilter(getResources().getColor(R.color.normalWhite));
            } else {
                setBackground(bright);
//                        setColorFilter(Color.parseColor("#3D474D"));
            }
        } else {
            if (isDark) {
                setColorFilter(getResources().getColor(R.color.normalWhite));
            } else {
                setColorFilter(Color.parseColor("#3D474D"));
            }
        }
    }
}