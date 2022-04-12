package com.zhuiyun.library

/**

 * @author: yun

 * @date: 2022/4/12 0012 13

 */
object ByteUtils {


    /**
     * 十六进制转十进制
     */
    fun hextoInt(s: String): Int {
        return s.toInt(16)
    }

    /**
     * byte转int
     */
    fun byteArrayInt2(low: Byte, high: Byte): Int {
        return (high.toInt() and (0xff shl 8) or (low.toInt() and 0xff))
    }


    /**
     * byte 转 int
     */
    fun byteArrayLong4(value1: Byte, value2: Byte, value3: Byte, value4: Byte): Long {
        return (value1 + value2 * 256 + value3 * 256 * 256 + value4 * 256 * 256 * 256).toLong()
    }


    /**
     * byte[]转String
     */
    fun bytesToHexString(src: ByteArray?): String? {
        val stringBuilder = StringBuilder("")
        if (src == null || src.isEmpty()) {
            return null
        }
        for (i in src.indices) {
            val v: Int = src[i].toInt() and 0xFF
            val hv = Integer.toHexString(v)
            if (hv.length < 2) {
                stringBuilder.append(0)
            }
            stringBuilder.append(hv)
        }
        return stringBuilder.toString()
    }

    /**
     * ascii码的byte转int
     */
    fun hextoInt(byte1: Byte, byte2: Byte): Int? {
        return ByteUtils.bytesToHex(byte1, byte2)?.toInt(16)
    }


    /**
     * 字节数组转16进制
     * @param
     * @return  转换后的Hex字符串
     */
    fun bytesToHex(byte1: Byte, byte2: Byte): String? {
        val sb = StringBuffer()
        val hex = Integer.toHexString(byte1.toInt() and 0xFF)
        if (hex.length < 2) {
            sb.append(0)
        } else {
            sb.append(hex)
        }
        val hex2 = Integer.toHexString(byte2.toInt() and 0xFF)
        if (hex2.length < 2) {
            sb.append(0)
        } else {
            sb.append(hex2)
        }
        return sb.toString()
    }
}