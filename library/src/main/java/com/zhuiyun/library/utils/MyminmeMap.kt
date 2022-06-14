package com.zhuiyun.library.utils

import com.zhuiyun.library.utils.MyminmeMap
import java.util.HashMap

/**
 * @author: yun
 * @date: 2022/5/19 0019 15
 */
object MyminmeMap {
    private val mapSimple = HashMap<String, String>()

    /**
     * 常用"文件扩展名—MIME类型"匹配表。
     * 注意，此表并不全，也并不是唯一的，就像有人喜欢用浏览器打开TXT一样，你可以根据自己的爱好自定义。
     */
    val minmeMap: HashMap<String, String>
        get() {
            if (mapSimple.size == 0) {
                mapSimple[".3gp"] = "video/3gpp"
                mapSimple[".apk"] = "application/vnd.android.package-archive"
                mapSimple[".asf"] = "video/x-ms-asf"
                mapSimple[".avi"] = "video/x-msvideo"
                mapSimple[".bin"] = "application/octet-stream"
                mapSimple[".bmp"] = "image/bmp"
                mapSimple[".c"] = "text/plain"
                mapSimple[".chm"] = "application/x-chm"
                mapSimple[".class"] = "application/octet-stream"
                mapSimple[".conf"] = "text/plain"
                mapSimple[".cpp"] = "text/plain"
                mapSimple[".doc"] = "application/msword"
                mapSimple[".docx"] = "application/msword"
                mapSimple[".exe"] = "application/octet-stream"
                mapSimple[".gif"] = "image/gif"
                mapSimple[".gtar"] = "application/x-gtar"
                mapSimple[".gz"] = "application/x-gzip"
                mapSimple[".h"] = "text/plain"
                mapSimple[".htm"] = "text/html"
                mapSimple[".html"] = "text/html"
                mapSimple[".jar"] = "application/java-archive"
                mapSimple[".java"] = "text/plain"
                mapSimple[".jpeg"] = "image/jpeg"
                mapSimple[".jpg"] = "image/jpeg"
                mapSimple[".js"] = "application/x-javascript"
                mapSimple[".log"] = "text/plain"
                mapSimple[".m3u"] = "audio/x-mpegurl"
                mapSimple[".m4a"] = "audio/mp4a-latm"
                mapSimple[".m4b"] = "audio/mp4a-latm"
                mapSimple[".m4p"] = "audio/mp4a-latm"
                mapSimple[".m4u"] = "video/vnd.mpegurl"
                mapSimple[".m4v"] = "video/x-m4v"
                mapSimple[".mov"] = "video/quicktime"
                mapSimple[".mp2"] = "audio/x-mpeg"
                mapSimple[".mp3"] = "audio/x-mpeg"
                mapSimple[".mp4"] = "video/mp4"
                mapSimple[".mpc"] = "application/vnd.mpohun.certificate"
                mapSimple[".mpe"] = "video/mpeg"
                mapSimple[".mpeg"] = "video/mpeg"
                mapSimple[".mpg"] = "video/mpeg"
                mapSimple[".mpg4"] = "video/mp4"
                mapSimple[".mpga"] = "audio/mpeg"
                mapSimple[".msg"] = "application/vnd.ms-outlook"
                mapSimple[".ogg"] = "audio/ogg"
                mapSimple[".pdf"] = "application/pdf"
                mapSimple[".png"] = "image/png"
                mapSimple[".pps"] = "application/vnd.ms-powerpoint"
                mapSimple[".ppt"] = "application/vnd.ms-powerpoint"
                mapSimple[".pptx"] = "application/vnd.ms-powerpoint"
                mapSimple[".prop"] = "text/plain"
                mapSimple[".rar"] = "application/x-rar-compressed"
                mapSimple[".rc"] = "text/plain"
                mapSimple[".rmvb"] = "audio/x-pn-realaudio"
                mapSimple[".rtf"] = "application/rtf"
                mapSimple[".sh"] = "text/plain"
                mapSimple[".tar"] = "application/x-tar"
                mapSimple[".tgz"] = "application/x-compressed"
                mapSimple[".txt"] = "text/plain"
                mapSimple[".wav"] = "audio/x-wav"
                mapSimple[".wma"] = "audio/x-ms-wma"
                mapSimple[".wmv"] = "audio/x-ms-wmv"
                mapSimple[".wps"] = "application/vnd.ms-works"
                mapSimple[".xml"] = "text/plain"
                mapSimple[".xls"] = "application/vnd.ms-excel"
                mapSimple[".xlsx"] = "application/vnd.ms-excel"
                mapSimple[".z"] = "application/x-compress"
                mapSimple[".zip"] = "application/zip"
                mapSimple[""] = "*/*"
            }
            return mapSimple
        }
}