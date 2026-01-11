package com.example.gmwrokouttimer.utils

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
        "%02d:%02d".format(  minutes, seconds)
    }
}

/*
fun main() {
    println(formatSeconds(130)) // Output: 02:10
    println(formatSeconds(65))  // Output: 01:05
    println(formatSeconds(5))   // Output: 00:05
}
 */