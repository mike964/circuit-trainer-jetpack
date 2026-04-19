package com.example.gmwrokouttimer.presentation.progress

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate


@Composable
fun Calendar2(
    modifier: Modifier = Modifier,
    ld: LocalDate = LocalDate.now(),  // selected month
    highlightedDays: List<Int>   // List of days to highlight listOf(1, 5, 10, 20, 25)
) {

    Log.d("xx", ld.toString())
//    val currentMonth = LocalDate.now().month
    val currentMonth = ld.month
    val totalDays = currentMonth.length(LocalDate.now().isLeapYear)
    Log.d("xx", totalDays.toString())  // 30

    val textMeasurer = rememberTextMeasurer()
    // 1. Define the rectangle's position and size
    val rectSize = Size(300f, 200f)
    val rectTopLeft = Offset(100f, 100f)
    val rect = Rect(rectTopLeft, rectSize)

    var rectColor by remember { mutableStateOf(Color.Blue) }

//    val highlightedDays = listOf(1, 5, 10, 20, 25)

    val activityDates = listOf(
        "2026-04-03T10:27:35.12Z",
        "2026-04-13T10:27:35.12Z",
        "2026-04-16T10:27:35.12Z"
    )
//    val highlightedDays = activityDates.map {
//        val instant = Instant.parse(it)
//        val date = instant.atZone(java.time.ZoneId.systemDefault()).toLocalDate()
//        date.dayOfMonth
//    }
//    Log.d("progress", highlightedDays.toString())
    // output : [3, 13, 16]


    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .border(1.dp, Color.DarkGray)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { tapOffset ->
                        // 2. Check if tap is inside the rectangle
                        if (rect.contains(tapOffset)) {
                            rectColor = if (rectColor == Color.Blue) Color.Red else Color.Blue
                        }
                    }
                )
            }
    ) {
        val width = size.width
        val height = size.height
        val cellWidth = size.width / 7
        val cellHeight = size.height / 5 // Common for months spanning 6 weeks

        val daysInMonth = 7
        val totalRow = (totalDays + daysInMonth) / 7

        val boxHeight = height / totalRow
        val boxWidth = width / daysInMonth

        Log.d("xx", boxHeight.toString())
        Log.d("xx", boxWidth.toString())

        for (days in 1..totalDays) {
            val row = (days - 1) / daysInMonth
            val col = (days - 1) % daysInMonth
            val x = col * boxWidth
            val y = row * boxHeight

            drawRect(
                color = if (days in highlightedDays) Color.Green else Color.LightGray, // Customize the color as needed
                topLeft = Offset(x + 1, y),
                size = Size(boxWidth - 2, boxHeight - 1),
//                style = Stroke(width = 4f), // Outline instead of fill
//                topLeft = Offset(50f, 50f), // Start drawing 50 pixels from left and top
//                size = Size(width = 300f, height = 200f) // Dimensions in pixels
            )
            drawText(
                textMeasurer = textMeasurer, text = "$days",
                topLeft = Offset(x + boxWidth / 3 + 10, y + boxHeight / 2.5f)
            )
        }
        // Horizontal lines
        for (i in 0..5) {
            drawLine(
                color = Color.Black,
                start = Offset(0f, i * cellHeight),
                end = Offset(size.width, i * cellHeight),
                strokeWidth = 1.dp.toPx()
            )
        }
    }
}

//@Preview
//@Composable
//private fun Calendar2Preview() {
//    Calendar2()
//}