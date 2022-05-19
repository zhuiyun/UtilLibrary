package com.zhuiyun.library.utils;

/**
 * @author: yun
 * @date: 2022/4/20 0020 09
 */
public class NumberUtils {
    public static int strToInt(String str){
        int result=0;
        try {
            result= (int) strToDouble(str);
        }catch (Exception e){
            return result;
        }
        return result;
    }


    public static double strToDouble(String str){
        double result=0.0;
        try {
            result=Double.parseDouble(str);
        }catch (Exception e){
            return result;
        }
        return result;
    }

    public static float strToFloat(String str){
        float result=0.0f;
        try {
            result=Float.parseFloat(str);
        }catch (Exception e){
            return result;
        }
        return result;
    }
}
