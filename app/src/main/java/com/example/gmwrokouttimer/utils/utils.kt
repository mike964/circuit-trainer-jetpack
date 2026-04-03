package com.example.gmwrokouttimer.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

fun formatSeconds(totalSeconds: Long): String {
    val duration = totalSeconds.seconds
    // toComponents splits the duration into hours, minutes, seconds
    return duration.toComponents { _, minutes, seconds, _ ->
        "%02d:%02d".format(minutes, seconds)
    }
}

fun formatMilliseconds(millis: Long): String {
    val duration = millis.milliseconds
    return duration.toComponents { hours, minutes, seconds, _ ->
        // Format as HH:mm:ss
//        "%02d:%02d:%02d".format(hours, minutes, seconds)
        "%02d:%02d".format(minutes, seconds)
    }
}

fun formatDateTime(millis: Long): String {
    val duration = millis.milliseconds
    return duration.toComponents { hours, minutes, seconds, _ ->
        // Format as HH:mm:ss
        "%02d:%02d:%02d".format(hours, minutes, seconds)
    }
}

fun formatDateString(inputDate: String): String {
    // 1. Parse the input string. 
    // LocalDateTime.parse() handles ISO-8601 (e.g., 2025-03-21T12:27:35...)
    val date = try {
        if (inputDate.contains("T")) {
            LocalDateTime.parse(inputDate)
        } else {
            val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
            LocalDateTime.parse(inputDate, inputFormatter)
        }
    } catch (e: Exception) {
        // Fallback or log error
        return inputDate
    }

    // 2. Define the output format and format the date object
    val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)
    return date.format(outputFormatter)
}


/*
fun main() {
    println(formatSeconds(130)) // Output: 02:10
    println(formatSeconds(65))  // Output: 01:05
    println(formatSeconds(5))   // Output: 00:05
}
 */