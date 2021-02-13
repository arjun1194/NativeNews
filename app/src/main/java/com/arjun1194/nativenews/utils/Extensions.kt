package com.arjun1194.nativenews.utils


import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): String {
    val timeZone = TimeZone.getTimeZone("GMT")
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        .apply {
            this.timeZone = timeZone
        }
    val date = try {
        simpleDateFormat.parse(this)!!
    } catch (e: Exception) {
        Date()
    }

    val newDateFormat = SimpleDateFormat("E, dd MMM yyyy", Locale.ENGLISH)

    return newDateFormat.format(date)

}

fun String.isTimeGreaterThan(
    hours: Int = 2
): Boolean {
    val dateAfterHours = Date(Date().time + hours * 60 * 60 * 1000)

    val simpleDateFormat = SimpleDateFormat()
    return try {
        val date = simpleDateFormat.parse(this)!!
        date.after(dateAfterHours)
    } catch (e: Exception) {
        false
    }


}


