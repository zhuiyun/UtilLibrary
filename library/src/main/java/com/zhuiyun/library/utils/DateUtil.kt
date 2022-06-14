package com.zhuiyun.library.utils

import com.zhuiyun.library.utils.DateUtil
import android.annotation.SuppressLint
import com.zhuiyun.library.utils.NumberUtils
import java.lang.Exception
import java.text.DateFormat
import java.text.ParseException
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * 日期工具
 *
 * @author xp
 * @describe 日期工具.
 * @date 2017/3/28.
 */
object DateUtil {
    var currentTime: Date? = null
    var format: String? = null
    var formatter: SimpleDateFormat? = null

    /**
     * 获取指定时间
     *
     * @param type 1 yyyy-MM-dd, 2 yyyy-MM-dd HH:mm, 3 yyyy-MM-dd HH:mm:ss
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    fun getAssignDate(time: Long, type: Int): String {
        currentTime = Date(time)
        when (type) {
            0 -> format = "HH:mm:ss"
            1 -> format = "yyyy-MM-dd"
            2 -> format = "yyyy-MM-dd HH:mm"
            3 -> format = "yyyy-MM-dd HH:mm:ss"
            else -> format = "yyyy-MM-dd HH:mm:ss"
        }
        formatter = SimpleDateFormat(format)
        return formatter!!.format(currentTime)
    }

    /**
     * 获取现在时间
     *
     * @param type 1 yyyy-MM-dd, 2 yyyy-MM-dd HH:mm, 3 yyyy-MM-dd HH:mm:ss
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    fun getNowDate(type: Int): String {
        val currentTime = Date()
        val format: String
        format = when (type) {
            0 -> "HH:mm:ss"
            1 -> "yyyy-MM-dd"
            2 -> "yyyy-MM-dd HH:mm"
            4 -> "yyyyMMddHHmmss"
            3 -> "yyyy-MM-dd HH:mm:ss"
            else -> "yyyy-MM-dd HH:mm:ss"
        }
        @SuppressLint("SimpleDateFormat") val formatter =
            SimpleDateFormat(format)
        return formatter.format(currentTime)
        //        ParsePosition pos = new ParsePosition(8);
//        return formatter.parse(dateString, pos);
    }

    /**
     * 获取昨天时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    val toNdayDate: String
        get() {
            val currentTime = Date()
            currentTime.time = currentTime.time - 24 * 60 * 60 * 1000L
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            return formatter.format(currentTime)
        }

    /**
     * 得到现在小时
     */
    val hour: String
        get() {
            val currentTime = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val dateString = formatter.format(currentTime)
            val hour: String
            hour = dateString.substring(11, 13)
            return hour
        }

    /**
     * 得到现在分钟
     */
    val time: String
        get() {
            val currentTime = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val dateString = formatter.format(currentTime)
            val min: String
            min = dateString.substring(14, 16)
            return min
        }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param sformat yyyyMMddhhmmss
     * @return 时间
     */
    fun getUserDate(sformat: String?): String {
        val currentTime = Date()
        val formatter = SimpleDateFormat(sformat)
        return formatter.format(currentTime)
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
     */
    fun getTwoHour(st1: String, st2: String): String {
        val kk: Array<String> = st1.split(":").toTypedArray()
        val jj: Array<String> = st2.split(":").toTypedArray()
        return if (kk[0].toInt() < jj[0].toInt()) {
            "0"
        } else {
            val y = kk[0].toDouble() + kk[1].toDouble() / 60
            val u = jj[0].toDouble() + jj[1].toDouble() / 60
            if (y - u > 0) {
                (y - u).toString()
            } else {
                "0"
            }
        }
    }

    /**
     * 得到二个日期间的间隔天数
     */
    fun getTwoDay(sj1: String?, sj2: String?): String {
        val myFormatter = SimpleDateFormat("yyyy-MM-dd")
        val day: Long
        day = try {
            val date = myFormatter.parse(sj1)
            val mydate = myFormatter.parse(sj2)
            (date.time - mydate.time) / (24 * 60 * 60 * 1000)
        } catch (e: Exception) {
            return ""
        }
        return day.toString() + ""
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    fun getPreTime(sj1: String?, jj: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var mydate1 = ""
        try {
            val date1 = format.parse(sj1)
            val Time = date1.time / 1000 + jj.toInt() * 60
            date1.time = Time * 1000
            mydate1 = format.format(date1)
        } catch (ignored: Exception) {
        }
        return mydate1
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     */
    fun getNextDay(nowdate: String?, delay: String): String {
        return try {
            val format = SimpleDateFormat("yyyy-MM-dd")
            val mdate: String
            val d = strToDate(nowdate)
            val myTime = d.time / 1000 + delay.toInt() * 24 * 60 * 60
            d.time = myTime * 1000
            mdate = format.format(d)
            mdate
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     */
    fun strToDate(strDate: String?): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val pos = ParsePosition(0)
        return formatter.parse(strDate, pos)
    }

    /**
     * 判断是否润年
     */
    fun isLeapYear(ddate: String?): Boolean {
        /**
         * 1.被400整除是闰年，否则：
         * 2.不能被4整除则不是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        val d = strToDate(ddate)
        val gc = Calendar.getInstance() as GregorianCalendar
        gc.time = d
        val year = gc[Calendar.YEAR]
        return year % 400 == 0 || year % 4 == 0 && year % 100 != 0
    }

    /**
     * 获取一个月的最后一天
     *
     * @param dat yyyy-MM-dd
     * @return str
     */
    fun getEndDateOfMonth(dat: String): String {
        var str = dat.substring(0, 8)
        val month = dat.substring(5, 7)
        val mon = month.toInt()
        str += if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            "31"
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            "30"
        } else {
            if (isLeapYear(dat)) {
                "29"
            } else {
                "28"
            }
        }
        return str
    }

    /**
     * 判断二个时间是否在同一个周
     *
     * @param date1 date
     * @param date2 date
     * @return boolean
     */
    fun isSameWeekDates(date1: Date?, date2: Date?): Boolean {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = date1
        cal2.time = date2
        val subYear = cal1[Calendar.YEAR] - cal2[Calendar.YEAR]
        if (0 == subYear) {
            return cal1[Calendar.WEEK_OF_YEAR] == cal2[Calendar.WEEK_OF_YEAR]
        } else if (1 == subYear && cal2[Calendar.MONTH] == 11) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            return cal1[Calendar.WEEK_OF_YEAR] == cal2[Calendar.WEEK_OF_YEAR]
        } else if (-1 == subYear && 11 == cal1[Calendar.MONTH]) {
            return cal1[Calendar.WEEK_OF_YEAR] == cal2[Calendar.WEEK_OF_YEAR]
        }
        return false
    }

    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     *
     * @return year + week
     */
    val seqWeek: String
        get() {
            val c = Calendar.getInstance(Locale.CHINA)
            var week = Integer.toString(c[Calendar.WEEK_OF_YEAR])
            if (week.length == 1) week = "0$week"
            val year = Integer.toString(c[Calendar.YEAR])
            return year + week
        }

    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     *
     * @param sdate date
     * @param num   0~7
     * @return 几号
     */
    fun getWeek(sdate: String?, num: String): String {
        // 再转换为时间
        val dd = strToDate(sdate)
        val c = Calendar.getInstance()
        c.time = dd
        if ("1" == num) // 返回星期一所在的日期
        {
            c[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
        } else if ("2" == num) // 返回星期二所在的日期
        {
            c[Calendar.DAY_OF_WEEK] = Calendar.TUESDAY
        } else if ("3" == num) // 返回星期三所在的日期
        {
            c[Calendar.DAY_OF_WEEK] = Calendar.WEDNESDAY
        } else if ("4" == num) // 返回星期四所在的日期
        {
            c[Calendar.DAY_OF_WEEK] = Calendar.THURSDAY
        } else if ("5" == num) // 返回星期五所在的日期
        {
            c[Calendar.DAY_OF_WEEK] = Calendar.FRIDAY
        } else if ("6" == num) // 返回星期六所在的日期
        {
            c[Calendar.DAY_OF_WEEK] = Calendar.SATURDAY
        } else if ("0" == num) // 返回星期日所在的日期
        {
            c[Calendar.DAY_OF_WEEK] = Calendar.SUNDAY
        }
        return SimpleDateFormat("yyyy-MM-dd").format(c.time)
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate yy-mm-dd
     * @return 星期几
     */
    fun getWeek(sdate: String?): String {
        // 再转换为时间
        val date = strToDate(sdate)
        val c = Calendar.getInstance()
        c.time = date
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return SimpleDateFormat("EEEE").format(c.time)
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate yy-mm-dd
     * @return 星期几
     */
    fun getWeekStr(sdate: String?): String {
        var str = ""
        str = getWeek(sdate)
        if ("1" == str) {
            str = "星期日"
        } else if ("2" == str) {
            str = "星期一"
        } else if ("3" == str) {
            str = "星期二"
        } else if ("4" == str) {
            str = "星期三"
        } else if ("5" == str) {
            str = "星期四"
        } else if ("6" == str) {
            str = "星期五"
        } else if ("7" == str) {
            str = "星期六"
        }
        return str
    }

    /**
     * 两个时间之间的天数
     *
     * @param date1 date1
     * @param date2 date2
     * @return 天数
     */
    fun getDays(date1: String?, date2: String?): Long {
        if (date1 == null || "" == date1) {
            return 0
        }
        if (date2 == null || "" == date2) {
            return 0
        }
        // 转换为标准时间
        val myFormatter = SimpleDateFormat("yyyy-MM-dd")
        var date: Date? = null
        var mydate: Date? = null
        try {
            date = myFormatter.parse(date1)
            mydate = myFormatter.parse(date2)
        } catch (ignored: Exception) {
        }
        assert(date != null)
        return (date!!.time - mydate!!.time) / (24 * 60 * 60 * 1000)
    }

    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     * 此函数返回该日历第一行星期日所在的日期
     *
     * @param sdate date
     * @return 该日历第一行星期日所在的日期
     */
    fun getNowMonth(sdate: String): String {
        // 取该时间所在月的一号
        var sdate = sdate
        sdate = sdate.substring(0, 8) + "01"

        // 得到这个月的1号是星期几
        val date = strToDate(sdate)
        val c = Calendar.getInstance()
        c.time = date
        val u = c[Calendar.DAY_OF_WEEK]
        return getNextDay(sdate, (1 - u).toString() + "")
    }

    /**
     * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
     *
     * @param k 表示是取几位随机数，可以自己定
     */
    fun getNo(k: Int): String {
        return getUserDate("yyyyMMddhhmmss") + getRandom(k)
    }

    /**
     * 返回一个随机数
     *
     * @param i 位数
     * @return 随机数
     */
    fun getRandom(i: Int): String {
        val jjj = Random()
        // int suiJiShu = jjj.nextInt(9);
        if (i == 0) return ""
        var jj = ""
        for (k in 0 until i) {
            jj = jj + jjj.nextInt(9)
        }
        return jj
    }

    /**
     * 日期格式转换yyyy-MM-dd'T'HH:mm:ss.SSSXXX  (yyyy-MM-dd'T'HH:mm:ss.SSSZ) TO  yyyy-MM-dd
     */
    fun dealDateFormat(oldDateStr: String): String {
        try {
            //此格式只有  jdk 1.7才支持  yyyy-MM-dd'T'HH:mm:ss.SSSXXX
            val df: DateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ") //yyyy-MM-dd'T'HH:mm:ss.SSSZ
            val date = df.parse(oldDateStr)
            val df1 = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK)
            val date1 = df1.parse(date.toString())
            val df2: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            return df2.format(date1)
        } catch (e: Exception) {
            e.printStackTrace()
        }

//        2017-10-18T16:52:14.566+08:00
        return oldDateStr
    }

    /**
     * 日期格式转换yyyy-MM-dd'T'HH:mm:ss.SSSXXX  (yyyy-MM-dd'T'HH:mm:ss.SSSZ) TO  yyyy-MM-dd HH:mm:ss
     */
    fun dealDateFormatToYYYY_MM_DD_hh_mm_ss(oldDateStr: String): String {
        try {
            //此格式只有  jdk 1.7才支持  yyyy-MM-dd'T'HH:mm:ss.SSSXXX
            val df: DateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ") //yyyy-MM-dd'T'HH:mm:ss.SSSZ
            val date = df.parse(oldDateStr)
            val df1 = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK)
            val date1 = df1.parse(date.toString())
            val df2: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return df2.format(date1)
        } catch (e: Exception) {
            e.printStackTrace()
        }

//        2017-10-18T16:52:14.566+08:00
        return oldDateStr
    }

    //字符串转时间戳
    fun getTime(timeString: String?): String {
        var timeStamp = ""
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val d: Date
        try {
            d = sdf.parse(timeString)
            timeStamp = d.time.toString()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return timeStamp
    }//获取系统时间的10位的时间戳

    //获取10位字符串格式的时间戳
    val timeC: String
        get() {
            val time = System.currentTimeMillis() / 1000 //获取系统时间的10位的时间戳
            return time.toString()
        }

    //获取当前时间，精确到秒，用于获取时间戳
    val dateTOSecond: String
        get() {
            val format1 =
                SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            return format1.format(Date())
        }
    val week1: String
        get() {
            val cal = Calendar.getInstance()
            val i = cal[Calendar.DAY_OF_WEEK]
            return when (i) {
                1 -> "星期日"
                2 -> "星期一"
                3 -> "星期二"
                4 -> "星期三"
                5 -> "星期四"
                6 -> "星期五"
                7 -> "星期六"
                else -> ""
            }
        }
    val nowTime: String
        get() {
            val format = SimpleDateFormat("HH:mm")
            return format.format(Date())
        }
    val date: String
        get() {
            val format1 = SimpleDateFormat("yyyy-MM-dd")
            return format1.format(Date())
        }
    val homeDate: String
        get() {
            val format1 = SimpleDateFormat("MM月dd日")
            return format1.format(Date())
        }

    /**
     * 计算两个时间的间隔，返回分钟
     *
     * @param t1 yyyy-MM-dd hh:mm:ss
     * @param t2 yyyy-MM-dd hh:mm:ss
     * @return
     */
    fun getMinInterval(t1: String?, t2: String?): Int {
        var i = getTime(t1).toLong() - getTime(t2).toLong()
        i = i / 1000 / 60
        return i.toInt()
    }

    fun stampToDate(s: String): String {
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val lt: Long = try {
            s.toLong()
        } catch (e: Exception) {
            0L
        }
        val date = Date(lt)
        res = simpleDateFormat.format(date)
        return res
    }

    /**
     * 将10 or 13 位时间戳转为时间字符串
     * convert the number 1407449951 1407499055617 to date/time format timestamp
     */
    fun timestamp2Date(str_num: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
        return if (str_num.length == 13) {
            sdf.format(Date(toLong(str_num)))
        } else {
            sdf.format(Date(NumberUtils.strToInt(str_num) * 1000L))
        }
    }

    /**
     * String转long
     *
     * @param obj
     * @return 转换异常返回 0
     */
    fun toLong(obj: String): Long {
        try {
            return obj.toLong()
        } catch (e: Exception) {
        }
        return 0
    }
}