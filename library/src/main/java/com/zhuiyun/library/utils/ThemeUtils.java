package com.inkbird.inkbirdapp.device.c236t.utils;

import android.view.View;
import android.view.ViewGroup;

import com.inkbird.inkbirdapp.base.widget.themeView.NightButton;
import com.inkbird.inkbirdapp.base.widget.themeView.NightConstraintLayout;
import com.inkbird.inkbirdapp.base.widget.themeView.NightConstraintLayoutRadius;
import com.inkbird.inkbirdapp.base.widget.themeView.NightConstraintLayoutWhite;
import com.inkbird.inkbirdapp.base.widget.themeView.NightEditText;
import com.inkbird.inkbirdapp.base.widget.themeView.NightEditTextRadius;
import com.inkbird.inkbirdapp.base.widget.themeView.NightFrameLayout;
import com.inkbird.inkbirdapp.base.widget.themeView.NightImageView;
import com.inkbird.inkbirdapp.base.widget.themeView.NightImageView2;
import com.inkbird.inkbirdapp.base.widget.themeView.NightLinearLayout;
import com.inkbird.inkbirdapp.base.widget.themeView.NightLinearLayoutRadius;
import com.inkbird.inkbirdapp.base.widget.themeView.NightRelativeLayout;
import com.inkbird.inkbirdapp.base.widget.themeView.NightRelativeLayoutRadius;
import com.inkbird.inkbirdapp.base.widget.themeView.NightRelativeLayoutWhite;
import com.inkbird.inkbirdapp.base.widget.themeView.NightScrollView;
import com.inkbird.inkbirdapp.base.widget.themeView.NightScrollViewRadius;
import com.inkbird.inkbirdapp.base.widget.themeView.NightTextView;
import com.inkbird.inkbirdapp.base.widget.themeView.NightTextViewRadius;

public class ThemeUtils {
    public static void checkViewGroup(ViewGroup fatherLayout, boolean dark){
        changeViewGroupTheme(fatherLayout,dark);
        int childCount = fatherLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = fatherLayout.getChildAt(i);
            if (view instanceof ViewGroup) {
                changeViewGroupTheme((ViewGroup) view, dark);
                checkViewGroup((ViewGroup) view, dark);
            } else {
                if (view instanceof NightButton) {
                    ((NightButton) view).changeTheme(dark);
                } else if (view instanceof NightEditText) {
                    ((NightEditText) view).changeTheme(dark);
                } else if (view instanceof NightEditTextRadius) {
                    ((NightEditTextRadius) view).changeTheme(dark);
                } else if (view instanceof NightImageView) {
                    ((NightImageView) view).changeTheme(dark);
                } else if (view instanceof NightImageView2) {
                    ((NightImageView2) view).changeTheme(dark);
                } else if (view instanceof NightTextView) {
                    ((NightTextView) view).changeTheme(dark);
                } else if (view instanceof NightTextViewRadius) {
                    ((NightTextViewRadius) view).changeTheme(dark);
                }
            }
        }
    }

    private static void changeViewGroupTheme(ViewGroup layout, boolean dark) {
        if (layout instanceof NightConstraintLayout) {
            ((NightConstraintLayout) layout).changeTheme(dark);
        } else if (layout instanceof NightConstraintLayoutRadius) {
            ((NightConstraintLayoutRadius) layout).changeTheme(dark);
        } else if (layout instanceof NightConstraintLayoutWhite) {
            ((NightConstraintLayoutWhite) layout).changeTheme(dark);
        } else if (layout instanceof NightFrameLayout) {
            ((NightFrameLayout) layout).changeTheme(dark);
        } else if (layout instanceof NightLinearLayout) {
            ((NightLinearLayout) layout).changeTheme(dark);
        } else if (layout instanceof NightLinearLayoutRadius) {
            ((NightLinearLayoutRadius) layout).changeTheme(dark);
        } else if (layout instanceof NightRelativeLayout) {
            ((NightRelativeLayout) layout).changeTheme(dark);
        } else if (layout instanceof NightRelativeLayoutRadius) {
            ((NightRelativeLayoutRadius) layout).changeTheme(dark);
        } else if (layout instanceof NightRelativeLayoutWhite) {
            ((NightRelativeLayoutWhite) layout).changeTheme(dark);
        } else if (layout instanceof NightScrollView) {
            ((NightScrollView) layout).changeTheme(dark);
        } else if (layout instanceof NightScrollViewRadius) {
            ((NightScrollViewRadius) layout).changeTheme(dark);
        }
    }
}
