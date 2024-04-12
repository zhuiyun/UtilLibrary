package com.inkbird.base.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;


import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

public class StringUtils {

    public static double String2Double(String string){
        double a=0.0;
        try {
           a=Double.parseDouble(string);
        }catch (Exception e){
           e.printStackTrace();
        }
        return a;
    }


    public static int String2Int(String string){
        int  a=0;
        try {
            a=Integer.parseInt(string);
        }catch (Exception e){
            Log.d("yun", "dealHistoryData: "+e.toString());
            e.printStackTrace();
        }
        return a;
    }
    public static int compareVersion(String v1, String v2) {
        String[] v1Str = v1.split("\\.");
        String[] v2Str = v2.split("\\.");

        int n = Math.max(v1Str.length, v2Str.length);
        for (int i = 0; i < n; i++) {
            int num1 = i < v1Str.length ? Integer.parseInt(v1Str[i]) : 0;
            int num2 = i < v2Str.length ? Integer.parseInt(v2Str[i]) : 0;

            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }
        }
        return 0;
    }

    @NonNull
    public static String intToHex(int n, int size) {
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            s = s.append(b[n % 16]);
            n /= 16;
        }
        a = s.reverse().toString();
        a = add_zore(a, size);
        return a;
    }

    //用于反转字符串高低位
    public static String resverByteHex(String str){
        StringBuilder stringBuilder=new StringBuilder();
        StringBuilder sb=new StringBuilder();
        if(str.length()%2!=0){
            stringBuilder.append("0").append(str);
        }else{
            stringBuilder.append(str);
        }
        for (int i = stringBuilder.length()-2; i >=0; i-=2) {
            sb.append(stringBuilder.substring(i,i+2));
        }
        return sb.toString();

    }

    public static String intToHexReverse(int n, int size) {
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            s = s.append(b[n % 16]);
            n /= 16;
        }
        a = s.reverse().toString();
        a = add_zore(a, size);
        return a;
    }

    public static String intToHex3(byte n, int size) {
        String a;
        a = Integer.toHexString(n);
        a = add_zore(a, size);
        if (a.length() > 4) {
            return a.substring(4, 8);
        }
        return a;
    }

    /**
     * CRC16校验
     *
     * @param bytes
     * @param len
     * @return
     */
    public static int crc16l(byte[] bytes, int len) {
        int i, j, tmp;
        int CRC16 = 0x0000ffff;
        for (i = 0; i < len; i++) {
            CRC16 = (bytes[i] & 0x000000ff) ^ CRC16;
            for (j = 0; j < 8; j++) {
                tmp = CRC16 & 0x00000001;
                CRC16 = CRC16 >> 1;
                if (tmp == 1) {
                    CRC16 = CRC16 ^ 0x0000a001;
                }
            }
        }
        return CRC16;
    }

    public static String hexToStr(String hexStr) {
        String str = "0123456789abcdef";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    @NonNull
    public static String intToHex2(int n, int size) {
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        while (n != 0) {
            s = s.append(b[n % 16]);
            n /= 16;
        }
        a = s.reverse().toString();
        a = add_zore(a, size);
        return a;
    }

    /**
     * @param n
     * @return 1 字节
     */
    public static String intToHex3(int n) {
        byte b = (byte) n;
        String value = Integer.toHexString(b);
        if (value.length() % 2 != 0) {
            value = "0" + value;
        }
        return value.substring(value.length() - 2);
    }

    @NonNull
    public static String intToLen(int n, int size) {
        if (String.valueOf(n).length() < size) {
            String str = "0" + n;
            str = add_zore(str, size);
            return str;
        } else {
            return String.valueOf(n);
        }
    }

    @NonNull
    public static String strToLen(@NonNull String str, int size) {
        if (str.length() < size) {
            int a = size - str.length();
            for (int i = 0; i < a; i++) {
                str += "0";
            }
        }
        return str;
    }

    public static String strToLens(@NonNull String str, int size) {
        if (str.length() < size) {
            int a = size - str.length();
            for (int i = 0; i < a; i++) {
                str = "0" + str;
            }
        }
        return str;
    }

    private static String add_zore(String str, int size) {
        if (str.length() < size) {
            str = "0" + str;
            str = add_zore(str, size);
        }
        return str;
    }

    @NonNull
    public static String signedNumberToLen(@NonNull String str, int size) {
        if (str.length() < size) {
            str = add_zore(str, size);
        } else if (str.length() > size) {
            str = str.substring(str.length() - size);
        }
        return str;
    }

    @NonNull
    public static String bytesToHexString(@NonNull byte[] array) {
        StringBuilder builder = new StringBuilder();

        for (byte b : array) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            builder.append(hex);
        }

        return builder.toString();
    }

    @Nullable
    public static byte[] hexStringToBytes(@NonNull String hex) {
        if (hex.isEmpty()) {
            return null;
        } else if (hex.length() % 2 != 0) {
            return null;
        } else {
            hex = hex.toUpperCase(Locale.ENGLISH);
            int len = hex.length() / 2;
            byte[] b = new byte[len];
            char[] hc = hex.toCharArray();
            for (int i = 0; i < len; i++) {
                int p = 2 * i;
                b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
            }
            return b;
        }
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    @Nullable
    public static String hex2Str(@NonNull String hex) {
        if (hex.isEmpty()) {
            return null;
        } else if (hex.length() % 2 != 0) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            hex = hex.toUpperCase(Locale.ENGLISH);
            int len = hex.length() / 2;
            for (int i = 0; i < len; i++) {
                int code = Integer.parseInt(hex.substring(i << 1, (i << 1) + 2), 16);
                sb.append((char) code);
            }
            return sb.toString();
        }
    }

    @Nullable
    public static String str2Hex(@NonNull String str) {
        if (str.isEmpty()) {
            return null;
        }
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes(StandardCharsets.UTF_8);
        int bit;

        for (byte b : bs) {
            bit = (b & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = b & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    private static String hexString = "0123456789abcdef";

    @NonNull
    public static String encode(@NonNull String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder(bytes.length << 1);
        for (byte aByte : bytes) {
            sb.append(hexString.charAt((aByte & 0xf0) >> 4));
            sb.append(hexString.charAt((aByte & 0x0f)));
        }
        return sb.toString();
    }

    @NonNull
    public static String encodeSingle(@NonNull String str) {
        byte workStatus = Integer.valueOf(str, 16).byteValue();
        char a = (char) ((workStatus & 0xf0) >> 4);
        char b = (char) (workStatus & 0x0f);
        StringBuilder sb = new StringBuilder();
        sb.append(hexString.charAt(a));
        sb.append(hexString.charAt(b));
        return sb.toString();
    }

    @NonNull
    public static String decode(@NonNull String bytes) {
        if (bytes != null && bytes.length() > 1) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
            for (int i = 0; i < bytes.length(); i += 2)
                baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
            return new String(baos.toByteArray(), StandardCharsets.UTF_8);
        } else {
            return "";
        }
    }

    @NonNull
    public static String hexToSize(@NonNull String str, int size) {
        if (str.length() < size) {
            str = "0" + str;
            str = hexToSize(str, size);
            return str;
        } else if (str.length() > size) {
            str = str.substring(str.length() - size);
            return str;
        } else {
            return str;
        }
    }

    public static String formatFloat(float f) {
        DecimalFormat fnum = new DecimalFormat("###0.0");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        fnum.setDecimalFormatSymbols(dfs);
        return fnum.format(f);
    }

    public static String getTodayDateTimes(long dateline) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return format.format(dateline);
    }

    public static String getDateWith_yyyyMMddHHmm(long dateline) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
        return format.format(dateline);
    }

    public static String getDateWith_MMDDyyyy(long dateline) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(dateline);
        calendar.setTime(date);
        StringBuilder builder = new StringBuilder();
        builder.append(calendar.get(Calendar.MONTH) + 1);
        builder.append("/");
        builder.append(calendar.get(Calendar.DAY_OF_MONTH));
        builder.append("/");
        builder.append(calendar.get(Calendar.YEAR));
        return builder.toString();
    }

    public static String getDateWith_MMddHHmm(long dateline) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm MM-dd", Locale.getDefault());
        return format.format(dateline);
    }

    public static String getTimeToHH(long dateline) {
        SimpleDateFormat format = new SimpleDateFormat("HH", Locale.getDefault());
        return format.format(dateline);
    }

    public static String getTimeToMM(long dateline) {
        SimpleDateFormat format = new SimpleDateFormat("mm", Locale.getDefault());
        return format.format(dateline);
    }

    /**
     * 时间格式化展示
     *
     * @param time 时间戳/1000
     * @return "HH:MM:ss"
     */
    public static String getTimeFormatHMS(long time) {
        int h = (int) (time / 3600);
        int m = (int) ((time / 60) % 60);
        int s = (int) (time % 60);
        return intToLen(h, 2) + ":" + intToLen(m, 2) + ":" + intToLen(s, 2);
    }

    /**
     * 16进制字符串转mac地址
     *
     * @param hexData 倒序源
     * @return
     */
    public static String hexToMacAddress(String hexData) {
        String address = "00:00:00:00:00:00";
        if (hexData.length() == 12) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < hexData.length() / 2; j++) {
                s.append(hexData.substring(hexData.length() - 2 * (j + 1), hexData.length() - 2 * j));
            }
            address = s.toString().toUpperCase().replaceAll("(\\w{2})(?=.)", "$1:");
        }
        return address;
    }

    /**
     * 16进制字符串转mac地址
     *
     * @param hexData 顺序源
     * @return
     */
    public static String hexToMacAddress2(String hexData) {
        String address = "00:00:00:00:00:00";
        if (hexData.length() == 12) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < hexData.length() / 2; j++) {
                s.append(hexData.substring(2 * j, 2 * (j + 1)));
            }
            address = s.toString().toUpperCase().replaceAll("(\\w{2})(?=.)", "$1:");
        }
        return address;
    }

    public static long dateToStamp(@NonNull String s) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = simpleDateFormat.parse(s);
            long ts = 0;
            if (date != null) {
                ts = date.getTime();
            }
            return ts;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long dateToStamp(@NonNull SimpleDateFormat simpleDateFormat, @NonNull String s) {
        try {
            Date date = simpleDateFormat.parse(s);
            long ts = 0;
            if (date != null) {
                ts = date.getTime();
            }
            return ts;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @NonNull
    public static String toHexColor(int color) {
        String R, G, B;
        StringBuffer sb = new StringBuffer();
        R = Integer.toHexString(Color.red(color));
        G = Integer.toHexString(Color.green(color));
        B = Integer.toHexString(Color.blue(color));
        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;
        sb.append("#");
        sb.append(R);
        sb.append(G);
        sb.append(B);
        return sb.toString();
    }

    /**
     * 16转2
     *
     * @param hexString int
     * @return
     */
    public static String hex2Binary(String hexString) {
        int ten = Integer.parseInt(hexString, 16);
        String two = Integer.toBinaryString(ten);
        if (!"0".equals(two)) {
            if (two.length() != 8) {
                while (two.length() < 8) {
                    two = "0" + two;
                }
            }
            return two;
        } else {
            return "00000000";
        }
    }

    /**
     * 16转2
     *
     * @param hexString long
     * @return 2进制倒序字符串
     */
    public static String hex2Binary2(String hexString) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < hexString.length() / 2; i++) {
            int hexInt = Integer.parseInt(hexString.substring(i * 2, (i + 1) * 2), 16);
            result.append(new StringBuffer(add_zore(Integer.toBinaryString(hexInt), 8)).reverse());
        }
        return result.toString();
    }

    /**
     * 16转2
     *
     * @param hexString int
     * @return
     */
    public static String hex2Binary3(String hexString) {
        int ten = Integer.parseInt(hexString, 16);
        String two = Integer.toBinaryString(ten);
        if (!"0".equals(two)) {
            if (two.length() != 16) {
                while (two.length() < 16) {
                    two = "0" + two;
                }
            }
            return two;
        } else {
            return "0000000000000000";
        }
    }

    /**
     * 16转2
     *
     * @param hexString int
     * @return
     */
    public static String reverseHex2Binary4(String hexString) {
        int ten = Integer.parseInt(hexString, 16);
        String two = Integer.toBinaryString(ten);
        if (!"0".equals(two)) {
            if (two.length() != 16) {
                while (two.length() < 16) {
                    two = "0" + two;
                }
            }
            return new StringBuilder(two).reverse().toString();
        } else {
            return "0000000000000000";
        }
    }

    /**
     * 判定字符串是否为base64字符串
     *
     * @param str
     * @return
     */
    public static boolean isBase64(String str) {
        //不能为空且去除空格后长度不能等于0
        if (str == null | str.trim().length() == 0) {
            return false;
        } else {
            //必须是4的倍数
            if (str.length() % 4 != 0) {
                return false;
            }
            char[] strChars = str.toCharArray();
            //如果包含=，=必须在末尾且不能多余2个
            if (str.contains("=")) {
                if (!str.endsWith("=")) {
                    return false;
                }
                String substring = str.substring(0, strChars.length - 2);
                if (substring.contains("=")) {
                    return false;
                }
            }
            for (char c : strChars) {
                //必须是a-zA-Z0-9+/=中的字符
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')
                        || c == '+' || c == '/' || c == '=') {
                    continue;
                } else {
                    return false;
                }
            }

            return str.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$");
        }
    }

    public static boolean isHex(String str) {
        return str.matches("^[0-9A-Fa-f]+$");
    }

    /**
     * 过滤涂鸦 raw型 dp点数据，判断是否为base64，是则转16，否则直接返回
     *
     * @param dpStr
     * @return
     */
    public static String ThingsDpStrFilter(String dpStr) {
        if (dpStr == null) return "";

        String result;

        if (isHex(dpStr)) {
            result = dpStr;
        } else {
//            result = bytesToHexString(Base64.decode(dpStr, Base64.DEFAULT));
            result = "";
        }

        return result;
    }

    /**
     * 十进制数据转换为16进制并高位在前，低位在后
     *
     * @param num 十进制数据
     * @return
     */
    public static String numToHex(int num) {
        String hex = "";
        while (num != 0) {
            String h = Integer.toString(num & 0xff, 16);
            if ((h.length() & 0x01) == 1)
                h = '0' + h;
            hex = hex + h;
            num = num >> 8;
        }
        return hex.equals("") ? "0" : hex;
    }


    //2转16
    public static String binary2Hex(String binaryString) {
        int ten = Integer.parseInt(binaryString, 2);
        String hex = Integer.toHexString(ten);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    /**
     * 取反
     */
    public static String parseHex2Opposite(String str) {
        String hex;
        //十六进制转成二进制
        byte[] er = parseHexStr2Byte(str);

        //取反
        byte erBefore[] = new byte[er.length];
        for (int i = 0; i < er.length; i++) {
            erBefore[i] = (byte) ~er[i];
        }

        //二进制转成十六进制
        hex = parseByte2HexStr(erBefore);

        // 如果不够校验位的长度，补0,这里用的是两位校验
        hex = (hex.length() < 2 ? "0" + hex : hex);

        return hex;
    }

    /**
     * 将二进制转换成十六进制
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将十六进制转换为二进制
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    //计算校验和
    public static String CheckSum(String hexStr) {
        String checkSum;
        int sum = 0;
        int len = hexStr.length();
        int num = 0;
        while (num < len) {
            String s = hexStr.substring(num, num + 2);
            sum += Integer.parseInt(s, 16);
            num += 2;
        }
        int mod = sum % 256;
        checkSum = Integer.toHexString(mod);
        if (checkSum.length() < 2) {
            checkSum = "0" + checkSum;
        }
        return checkSum;
    }

    public static int dpToPx(Context context, double dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f * (dp >= 0 ? 1 : -1));
    }

    public static int lastBitData(int num) {
        String binaryNum = Integer.toBinaryString(num); // 将数字转换为二进制表示形式
        char highestBitChar = binaryNum.charAt(binaryNum.length() - 1); // 获取最后一位（也就是最高位）
        int highestBitInt = Character.getNumericValue(highestBitChar); // 将最高位的字符转换为对应的十进制值
        return highestBitInt;
    }

    /**
     * @param b      一个字节：10011001
     * @param start  起始bit位。如0位
     * @param length 需要的bit的长度。如5个bit--------> 11001
     * @return
     */
//b为传入的字节，start是起始位，length是长度，如要获取bit0-bit4的值，则start为0，length为5
    public static int getBits(byte b, int start, int length) {
        //字节b有8位bit，右移start位，截取长度为5的bit
        //10011001 右移 0位,还是10011001
        //0xFF的二进制为  11111111（8个1），右移8-5的长度，变为：00011111
        //      10011001
        //  &   00011111
        //      00011001   --------> bit
        int bit = (int) ((b >> start) & (0xFF >> (8 - length)));
        return bit;
    }


    //将长度转换为时间
    public static String stringForTime(int timeMs) {
        //将长度转换为时间
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

        int totalSeconds = timeMs / 1000;

//        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d hrs %02d min", hours, minutes).toString();
        } else {
            return mFormatter.format("%02d min", minutes).toString();
        }
//        else {
//            return mFormatter.format("%02d s", minutes, seconds).toString();
//        }
    }


    /**
     * 将字符串转换成Bitmap类型
     *
     * @param string
     * @return
     */
    public static Bitmap stringtoBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 将Bitmap转换成字符串
     *
     * @param bitmap
     * @return
     */
    public static String bitmaptoString(Bitmap bitmap) {
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    public static int byte2Int(byte aByte, byte aByte1) {
        return (int) ((aByte & 0xFF) | ((aByte1 & 0xFF)<<8));
    }
}