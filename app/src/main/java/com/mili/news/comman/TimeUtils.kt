package com.mili.news.comman

import androidx.annotation.NonNull
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object TimeUtils {

    private fun getAppropriateTimeDiffResolution(start: Date?, end: Date?): String {
        return if (start != null && end != null) {
            val diffInMs: Long = end.time - start.time
            val diffInMins: Long = TimeUnit.MILLISECONDS.toMinutes(diffInMs)
            val diffInHrs: Long = TimeUnit.MILLISECONDS.toHours(diffInMs)
            val diffInDays: Long = TimeUnit.MILLISECONDS.toDays(diffInMs)
            val stringBuilder = StringBuilder()
            if (diffInMins < 60) {
                if (diffInMins > 1) stringBuilder.append(diffInMins)
                    .append(" Mins Ago")
                    .toString() else if (diffInMins == 0L) "Now" else stringBuilder.append(
                    diffInMins
                ).append(" Min Ago").toString()
            } else if (diffInHrs < 24) {
                if (diffInHrs > 1)
                    stringBuilder.append(diffInHrs)
                        .append(" Hours Ago")
                        .toString() else {
                    stringBuilder.append(
                        diffInHrs
                    ).append(" Hour Ago").toString()
                }
            } else if (diffInDays < 30) {
                if (diffInDays > 1) {
                    stringBuilder.append(diffInDays)
                        .append(" Days Ago").toString()
                } else {
                    stringBuilder.append(diffInDays)
                        .append(" Day Ago").toString()
                }
            } else {
                "--"
            }
        } else {
            "--"
        }
    }

    fun getFormattedTime(@NonNull time: String): String {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.ENGLISH)
        input.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
        var d: Date?
        try {
            d = input.parse(time)
            return getAppropriateTimeDiffResolution(d, Date())
        } catch (e: ParseException) {
            try {
                val fallback =
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
                fallback.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
                d = fallback.parse(time)
                return getAppropriateTimeDiffResolution(d, Date())
            } catch (e2: ParseException) {
                return "--"
            }
        }
    }
}