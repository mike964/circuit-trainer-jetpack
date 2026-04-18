package com.example.gmwrokouttimer.presentation.progress

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun Calendar2(modifier: Modifier = Modifier, ld: LocalDate = LocalDate.now()) {

    Log.d("xx", ld.toString())
//    val currentMonth = LocalDate.now().month
    val currentMonth = ld.month
    val totalDays = currentMonth.length(LocalDate.now().isLeapYear)
    Log.d("xx", totalDays.toString())  // 30

    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        val width = size.width
        val height = size.height

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
                color = Color.Yellow, // Customize the color as needed
                topLeft = Offset(x, y),
                size = Size(boxWidth, boxHeight),
                style = Stroke(width = 2f) // Outline instead of fill
//                topLeft = Offset(50f, 50f), // Start drawing 50 pixels from left and top
//                size = Size(width = 300f, height = 200f) // Dimensions in pixels
            )

            drawText(
                textMeasurer = textMeasurer, text = "$days",
                topLeft = Offset(x + boxWidth / 3 + 10, y + boxHeight / 2.5f)
            )
        }
    }
}

@Preview
@Composable
private fun Calendar2Preview() {
    Calendar2()
}