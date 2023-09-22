package com.example.image_search

import android.content.Context
import android.util.Log
import com.jblee.imagesearch.Constants.Pref_key
import com.jblee.imagesearch.Constants.Prefs_Name
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object Util {

    fun getDateTimestampWithFormat(
        timestamp: String?,
        fromFormatformat: String?,
        toFormatformat: String?
    ): String {
        var date: Date? = null
        var res = ""
        try {
            val format = SimpleDateFormat(fromFormatformat)
            date = format.parse(timestamp)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val df = SimpleDateFormat(toFormatformat)
        res = df.format(date)
        return res
    }
    fun saveLastSearch(context: Context, query: String) {
        val prefs = context.getSharedPreferences(Prefs_Name, Context.MODE_PRIVATE)
        prefs.edit().putString(Pref_key, query).apply()
    }

    fun getLastSearch(context: Context): String? {
        val prefs = context.getSharedPreferences(Prefs_Name, Context.MODE_PRIVATE)
        return prefs.getString(Pref_key, null)
    }
}