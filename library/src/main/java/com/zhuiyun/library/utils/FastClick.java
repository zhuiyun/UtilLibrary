package com.inkbird.base.utils;

public class FastClick {
    private static long lastClickTime = 0;//上次点击的时间

    private static int spaceTime = 200;//时间间隔

    public static boolean isFastClick() {
        long currentTime = System.currentTimeMillis();//当前系统时间

        boolean isAllowClick;//是否允许点击

        if (currentTime - lastClickTime > spaceTime) {
            isAllowClick = false;

        } else {
            isAllowClick = true;

        }

        lastClickTime = currentTime;

        return isAllowClick;
    }

    public static boolean isFastClick(int spaceTiming) {
        long currentTime = System.currentTimeMillis();//当前系统时间

        boolean isAllowClick;//是否允许点击

        if (currentTime - lastClickTime > spaceTiming) {
            isAllowClick = false;

        } else {
            isAllowClick = true;

        }

        lastClickTime = currentTime;

        return isAllowClick;
    }
}