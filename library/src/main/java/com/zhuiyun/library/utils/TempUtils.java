package com.inkbird.inkbirdapp.device.ibsth3.utils;

import android.util.Log;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TempUtils {
    public static float CToF(float C) {
        float f = 1.8f * C + 32;
        if (!isValidNumber(f)) {
            BigDecimal b = new BigDecimal(f);
            float f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
            //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
            return f1;
        }
        return 0;
    }

    public static double onlyCToF(double C) {
//        if()
        return  1.8 * C + 32;
//        if (!isValidNumber(f)) {
//            BigDecimal b = new BigDecimal(f);
//            float f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
//            //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
//            return f1;
//        }
//        return 0;
    }


    public static float CToF4(float C) {
        float f = 1.8f * C + 32;
        if (!isValidNumber(f)) {
            BigDecimal b = new BigDecimal(f);
            float f1 = b.setScale(1, BigDecimal.ROUND_DOWN).floatValue();
            //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
            return f1;
        }
        return 0;
    }

    public static double CToF5(double C) {
        double f = 1.8 * C + 32;
        if (!isValidNumber(f)) {
            BigDecimal b = new BigDecimal(f);
            double f1 = b.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
            //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
            return f1;
        }
        return 0;
    }

    public static float CToF2(float C) {
        float f = 1.8f * C + 32;
        if (!isValidNumber(f)) {
            BigDecimal b = new BigDecimal(f);
            float f1 = b.setScale(1, BigDecimal.ROUND_DOWN).floatValue();
            //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
            return f1;
        }
        return 0;
    }

    public static String CToF(float C, boolean bl) {
        int db = (int) (C * 10);
        int f = 18 * db;
        BigDecimal b = new BigDecimal(f / 100f + 32);
        double f1 = b.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
        //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
        return String.valueOf(f1);
    }

    public static double CToF(double C) {
        double f = 1.8 * C + 32;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
        return f1;
    }



    public static String formatFloat(double f) {
        DecimalFormat df = new DecimalFormat("####.##");
        return df.format(f);
    }

    public static String formatFloat(float f) {
        DecimalFormat df = new DecimalFormat("####.##");
        return df.format(f);
    }

    public static String formatFloat1(float f) {
        DecimalFormat df = new DecimalFormat("####.#");
        String value = df.format(f);

        return value.equals("-0") ? "0" : value;
    }

    public static int CToF(int C) {
        double f = 1.8 * C + 32;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
        return (int) f1;
    }

    public static String CToF3(float C) {
        double f = 1.8 * C + 32;
        BigDecimal b = new BigDecimal(f);
        double f1;
        if ((int) (f * 10) % 10 == 0) {
            f1 = b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
            return String.valueOf((int) f1);
        } else {
            f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            return String.valueOf(f1);
        }
    }

    public static int FToInt(float f) {
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
        //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
        return (int) f1;
    }

    public static int FToIntDown(float f) {
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
        //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
        return (int) f1;
    }

    public static float DoubleToFloatDown(double f) {
        BigDecimal b = new BigDecimal(f);
        //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
        return b.setScale(1, BigDecimal.ROUND_DOWN).floatValue();
    }

    public static float numberScale(float f) {
        BigDecimal bigDecimal = new BigDecimal(f);
        BigDecimal b = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP);
        return b.floatValue();
    }

    public static float numberScale3(float f) {
        if (Double.isNaN(f)) f = 0;
        BigDecimal bigDecimal = new BigDecimal(f);
        BigDecimal b = bigDecimal.setScale(1, BigDecimal.ROUND_DOWN);
        return b.floatValue();
    }
    public static double numberScale3Double(double f) {
        if (Double.isNaN(f)) f = 0;
        BigDecimal bigDecimal = new BigDecimal(f);
        BigDecimal b = bigDecimal.setScale(1, BigDecimal.ROUND_DOWN);
        return b.doubleValue();
    }

    public static float numberScale2(float f) {
        BigDecimal bigDecimal = new BigDecimal(f);
        BigDecimal b = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return b.floatValue();
    }

    public static int CToF2(int C) {
        return (int) (1.8f * C + 32);
    }

    public static int FToC(int F) {
        return (int) ((F - 32) / 1.8f);
    }

    public static float FToC(float F) {
        float c = (F - 32) / 1.8f;
        BigDecimal b = new BigDecimal(c);
        float f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }

    public static float newFToC(float F) {
        float c = (F - 32) / 1.8f;
        DecimalFormat df = new DecimalFormat("#.#");
        String format = df.format(c);
        float result = 0;
        try {
            result = Float.parseFloat(format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
        return result;

    }

    public static float FToC3(float F) {
        float c = (F - 32) / 1.8f;
        BigDecimal b = new BigDecimal(c);
        float f1 = b.setScale(0, BigDecimal.ROUND_HALF_UP).floatValue();
        return (int) f1;
    }

    public static float FToC4(float F) {
        float c = (F - 32) / 1.8f;
        BigDecimal b = new BigDecimal(c);
        float f1 = b.setScale(1, BigDecimal.ROUND_DOWN).floatValue();
        return  f1;
    }

    public static double FToC5(double F) {
        double c = (F - 32) / 1.8;
        BigDecimal b = new BigDecimal(c);
        double f1 = b.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
        return  f1;
    }

    public static float FToC2(float F) {
        float c = (F - 32) / 1.8f;
        BigDecimal b = new BigDecimal(c);
        float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }

    public static float FToC1(float F) {
        float c = (F - 32) / 1.8f;
        BigDecimal b = new BigDecimal(c);
        float f1 = b.setScale(0, BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }

    public static double RetainDecimals(double f) {
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
        return f1;
    }

    public static boolean isValidNumber(Number number) {
        if (number instanceof Double) {
            return ((Double) number).isInfinite() || ((Double) number).isNaN();
        } else if (number instanceof Float) {
            return ((Float) number).isInfinite() || ((Float) number).isNaN();
        }
        return true;
    }
}