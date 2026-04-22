package com.example.gmwrokouttimer.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
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

fun formatDateString(inputDate: String, outputFormat: String = "MMM dd, yyyy"): String {
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
    val outputFormatter = DateTimeFormatter.ofPattern(outputFormat, Locale.ENGLISH)
    return date.format(outputFormatter)
}

fun formatDate(inputDate: LocalDate, outputFormat: String = "MMM dd, yyyy"): String {
    val outputFormatter = DateTimeFormatter.ofPattern(outputFormat, Locale.ENGLISH)
    return inputDate.format(outputFormatter)
}

// # Convert Epoch millis to local date
fun convertEpochMillisToLocalDate(epochMillis: Long, outputFormat: String = "yyyy-MM-dd HH:mm"): String {
    val instant = Instant.ofEpochMilli(epochMillis)
    // 2. Apply a Time Zone (e.g., UTC or System Default)
    val dateTime = instant.atZone(ZoneId.of("UTC"))
    // 3. Format to Human Readable String
    val formatter = DateTimeFormatter.ofPattern(outputFormat, Locale.getDefault())
        .withZone(ZoneId.systemDefault())
    formatter.format(instant)
    return dateTime.format(formatter)
}

fun convertDateTimeToEpochMillis(dateTime: String): Long {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.getDefault())
    val localDateTime = LocalDateTime.parse(dateTime, formatter)
    val instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()
    return instant.toEpochMilli()
}

fun convertDateTimeToEpochMillis2(date: String, time: String): Long {
    val time_ = if (time.length ==4 ) {
        "0$time"  // Fix for 3:30 -> 03:00
    } else {
        time
    }
    // "2025-04-21", "12:27"
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.getDefault())
    val localDateTime = LocalDateTime.parse("$date $time_", formatter)
    val instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()
    return instant.toEpochMilli()
}

/*
fun main() {
    println(formatSeconds(130)) // Output: 02:10
    println(formatSeconds(65))  // Output: 01:05
    println(formatSeconds(5))   // Output: 00:05
}
 */