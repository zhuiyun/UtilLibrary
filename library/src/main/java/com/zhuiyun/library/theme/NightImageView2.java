package com.inkbird.inkbirdapp.base.widget.themeView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.inkbird.base.sp.SharedPreferencesUtil;
import com.inkbird.inkbirdapp.R;

@SuppressLint("AppCompatCustomView")
public class NightImageView2 extends ImageView {
    Drawable bright_bitmap;
    Drawable dark_bitmap;
    int bright_color;
    int dark_color;
    public NightImageView2(Context context) {
        this(context, null);
    }

    public NightImageView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NightImageView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NightImageView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttribute(context, attrs);
    }

    public void initAttribute(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.nightView2);
        String deviceType = a.getString(R.styleable.nightView2_device_type);
        bright_bitmap = a.getDrawable(R.styleable.nightView2_bright_res);
        dark_bitmap = a.getDrawable(R.styleable.nightView2_dark_res);
        bright_color = a.getColor(R.styleable.nightView2_bright_color, 0);
        dark_color = a.getColor(R.styleable.nightView2_dark_color, 0);
        if (!TextUtils.isEmpty(deviceType)) {
            if (!deviceType.isEmpty()) {
                boolean isNight = SharedPreferencesUtil.getBoolean(context, "night_" + deviceType, false);
                if (bright_bitmap != null && dark_bitmap != null) {
                    if (isNight) {
                        setImageDrawable(dark_bitmap);
                    } else {
                        setImageDrawable(bright_bitmap);
                    }
                } else if (bright_color != 0 && dark_color != 0) {
                    if (isNight) {
                        setColorFilter(dark_color, PorterDuff.Mode.SRC_ATOP);
                    } else {
                        setColorFilter(bright_color, PorterDuff.Mode.SRC_ATOP);
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
        if (bright_bitmap != null && dark_bitmap != null) {
            if (isDark) {
                setImageDrawable(dark_bitmap);
            } else {
                setImageDrawable(bright_bitmap);
            }
        } else if (bright_color != 0 && dark_color != 0) {
            if (isDark) {
                setColorFilter(dark_color, PorterDuff.Mode.SRC_ATOP);
            } else {
                setColorFilter(bright_color, PorterDuff.Mode.SRC_ATOP);
            }
        } else {
            if (isDark) {
                setColorFilter(getResources().getColor(R.color.normalWhite), PorterDuff.Mode.SRC_ATOP);
            } else {
                setColorFilter(Color.parseColor("#3D474D"), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }
}