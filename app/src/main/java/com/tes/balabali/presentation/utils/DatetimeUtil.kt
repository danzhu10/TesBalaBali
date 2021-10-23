package com.tes.balabali.presentation.utils

import android.annotation.SuppressLint
import com.tes.balabali.R
import com.tes.balabali.appContext
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object DatetimeUtil {

    val DATE_PATTERN = "yyyy-MM-dd"
    var DATE_PATTERN_SS = "yyyy-MM-dd HH:mm:ss"
    var DATE_PATTERN_MM = "yyyy-MM-dd'T'HH:mm:ss"

    val now: Date
        get() = Date(Date().time)

    val nows: Date
        get() = formatDate(DATE_PATTERN, now)

    @SuppressLint("SimpleDateFormat")
    fun formatDate(date: Date?, formatStyle: String): String {
        return if (date != null) {
            val sdf = SimpleDateFormat(formatStyle)
            sdf.format(date)
        } else {
            ""
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDate(date: Long, formatStyle: String): String {
        val sdf = SimpleDateFormat(formatStyle)
        return sdf.format(Date(date))
    }

    fun formatDate(formatStyle: String, formatStr: String): Date {
        val format = SimpleDateFormat(formatStyle, Locale.CHINA)
        return try {
            val date = Date()
            date.time = format.parse(formatStr).time
            date
        } catch (e: Exception) {
            println(e.message)
            nows
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDate(formatStyle: String, date: Date?): Date {
        return if (date != null) {
            val sdf = SimpleDateFormat(formatStyle)
            val formatDate = sdf.format(date)
            try {
                sdf.parse(formatDate)
            } catch (e: ParseException) {
                e.printStackTrace()
                Date()
            }

        } else {
            Date()
        }
    }

    fun stampToDate(s: String): Date {
        val lt = s.toLong()
        return Date(lt)
    }

    fun getCustomTime(dateStr: String): Date {
        return formatDate(DATE_PATTERN, dateStr)
    }

    fun convertDateWithDay(date: String?, tipe: String?): String? {
        val dayArr = arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
        var year = 0
        var mont = 0
        var day = 0
        var h = 0
        var min = 0
        var s = 0
        var dayPos = 0
        var dayName: String
        val convertedDate: Date
        val df: SimpleDateFormat = if (tipe == "tk") {
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH) //2020-10-15 15:04:18
        } else {
            SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        }
//        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            convertedDate = df.parse(date)
            val calendar = Calendar.getInstance()
            calendar.time = convertedDate
            year = calendar[Calendar.YEAR]
            mont = calendar[Calendar.MONTH] + 1
            day = calendar[Calendar.DAY_OF_MONTH]
            dayPos = calendar[Calendar.DAY_OF_WEEK] - 1

            h = calendar[Calendar.HOUR_OF_DAY]
            min = calendar[Calendar.MINUTE]
            s = calendar[Calendar.SECOND]
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return if (tipe == "tk") {
            dayArr[dayPos] + " " + day + " " + numToFullMonthName(
                mont
            ) + " " + year + " - " + String.format("%02d:%02d", h, min)
        } else {
            dayArr[dayPos] + " " + day + " " + numToFullMonthName(
                mont
            ) + " " + year
        }
    }

    fun convertDate2(date: String?): String? {
        var year = 0
        var mont = 0
        var day = 0
        val convertedDate: Date
        val df: SimpleDateFormat =
            SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH)

        try {
            convertedDate = df.parse(date)
            val calendar = Calendar.getInstance()
            calendar.time = convertedDate
            year = calendar[Calendar.YEAR]
            mont = calendar[Calendar.MONTH] + 1
            day = calendar[Calendar.DAY_OF_MONTH]
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return "$day " + numToFullMonthName(
            mont
        ) + " " + year
    }

    fun convertDate(date: String?): String {
        var year = 0
        var mont = 0
        var day = 0
        val convertedDate: Date
        val df: SimpleDateFormat =
            SimpleDateFormat(DATE_PATTERN_MM, Locale.ENGLISH)

        try {
            convertedDate = df.parse(date)
            val calendar = Calendar.getInstance()
            calendar.time = convertedDate
            year = calendar[Calendar.YEAR]
            mont = calendar[Calendar.MONTH] + 1
            day = calendar[Calendar.DAY_OF_MONTH]
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return "$day " + numToFullMonthName(
            mont
        ) + " " + year
    }

    private fun numToFullMonthName(mont: Int): String? {
        val month = appContext.resources.getStringArray(R.array.monthLongName)
        return month[mont - 1]
    }
}