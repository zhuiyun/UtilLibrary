package com.inkbird.base.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.core.os.ConfigurationCompat;

import com.inkbird.base.sp.SharedPreferencesUtil;

import java.util.Locale;

public class LanguageUtils {

    //切换语言
    public static void shiftLanguage(String language, Context context) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Locale locale1 = new Locale(language);
        config.setLocale(locale1);
//        context.createConfigurationContext(config);
        resources.updateConfiguration(config, dm);

    }


    //获取本地默认语言
    public static String getLocalLanguage() {
        return ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0).getLanguage();
    }


    //读取本地缓存语言
    public static void changeLanguage(Context context) {
        String language = SharedPreferencesUtil.getString(context, "language", "");
        if (TextUtils.isEmpty(language)) {
            String currentLanguage = LanguageUtils.getLocalLanguage();
            if (LanguageSupport.getInstance(context).supportMap.containsValue(currentLanguage)) {
                LanguageUtils.shiftLanguage(currentLanguage, context);
            } else {
                LanguageUtils.shiftLanguage("en", context);
            }
        } else {
            LanguageUtils.shiftLanguage(LanguageSupport.getInstance(context).supportMap.getOrDefault(language, "en"), context);
        }

    }


    //获取设置的语言
    public static String getSetLocalLanguage(Context context) {
        String language = SharedPreferencesUtil.getString(context, "language", "");
        if (TextUtils.isEmpty(language)) {
            return getLocalLanguage();
        } else {
            Locale locale = new Locale(LanguageSupport.getInstance(context).supportMap.getOrDefault(language, "en"));
            return locale.getLanguage();
        }

    }

    public static Context getConfigurationContext(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        LanguageUtils.changeLanguage(context);
        return context.createConfigurationContext(configuration);
    }

}
