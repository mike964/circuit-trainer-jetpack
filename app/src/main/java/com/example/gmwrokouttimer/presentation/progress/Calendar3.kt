package com.example.gmwrokouttimer.presentation.progress

import android.graphics.Paint
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.collections.listOf
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import com.example.gmwrokouttimer.ui.theme.LightGrey
import com.example.gmwrokouttimer.ui.theme.orange
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

private const val CALENDAR_ROWS = 5
private const val CALENDAR_COLS = 7

data class CalendarInput(val day: Int, val toDos: List<String> = emptyList())

@Composable
fun Calendar3(
    modifier: Modifier = Modifier,
//    startFromSunday: Boolean = true,
    calendarInput: List<CalendarInput>,
    onDayClick: (Int) -> Unit,
    strokeWidth: Float = 8f,
    month: String = "",
) {
    var canvasSize by remember {
        mutableStateOf(Size.Zero)
    }
    var clickAnimationOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    var animationRadius by remember {
        mutableFloatStateOf(0f)
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = month, fontWeight = FontWeight.SemiBold, fontSize = 40.sp)
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectTapGestures(
                        onTap = { offset ->
                            val column =
                                (offset.x / canvasSize.width * CALENDAR_COLS).toInt() + 1
                            val row = (offset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1
                            val day = column + (row - 1) * CALENDAR_COLS
                            if (day <= calendarInput.size) {
                                onDayClick(day)
                                clickAnimationOffset = offset
                                scope.launch {
                                    animate(0f, 225f, animationSpec = tween(300)) { value, _ ->
                                        animationRadius = value
                                    }
                                }
                            }
                        }
                    )
                }
        ) {
            val canvasHeight = size.height
            val canvasWidth = size.width
            canvasSize = Size(canvasWidth, canvasHeight)
            val ySteps = canvasHeight / CALENDAR_ROWS
            val xSteps = canvasWidth / CALENDAR_COLS

            val column = (clickAnimationOffset.x / canvasSize.width * CALENDAR_COLS).toInt() + 1
            val row = (clickAnimationOffset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1

            val path = Path().apply {
                moveTo((column - 1) * xSteps, (row - 1) * ySteps)
                lineTo(column * xSteps, (row - 1) * ySteps)
                lineTo(column * xSteps, row * ySteps)
                lineTo((column - 1) * xSteps, row * ySteps)
                close()
            }

            clipPath(path) {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(orange.copy(0.8f), orange.copy(0.2f)),
                        center = clickAnimationOffset,
                        radius = animationRadius + 0.1f
                    ),
                    radius = animationRadius + 0.1f,
                    center = clickAnimationOffset
                )
            }

            drawRoundRect(
                orange,
                cornerRadius = CornerRadius(25f, 25f),
                style = Stroke(
                    width = strokeWidth
                )
            )

            for (i in 1 until CALENDAR_ROWS) {
                drawLine(
                    color = orange,
                    start = Offset(0f, ySteps * i),
                    end = Offset(canvasWidth, ySteps * i),
                    strokeWidth = strokeWidth
                )
            }
            for (i in 1 until CALENDAR_COLS) {
                drawLine(
                    color = orange,
                    start = Offset(xSteps * i, 0f),
                    end = Offset(xSteps * i, canvasHeight),
                    strokeWidth = strokeWidth
                )
            }
            val textHeight = 17.dp.toPx()
            for (i in calendarInput.indices) {
                val textPositionX = xSteps * (i % CALENDAR_COLS) + strokeWidth
                val textPositionY = (i / CALENDAR_COLS) * ySteps + textHeight + strokeWidth / 2
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        "${i + 1}",
                        textPositionX,
                        textPositionY,
                        Paint().apply {
                            textSize = textHeight
//                            color = white.toArgb()
                            isFakeBoldText = true
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun CalendarBox() {
    // 1. Get the current YearMonth
    val currentMonth = YearMonth.now()
    // 2. Get the number of days in that specific month
    val daysInMonth = currentMonth.lengthOfMonth()
    // 3. Generate a list of days (1 to 28/30/31)
    val daysList = (1..daysInMonth).toList()

    val calendarInputList by remember { mutableStateOf(createCalendarList(daysInMonth)) }
    var clickedCalendarElem by remember {
        mutableStateOf<CalendarInput?>(null)
    }

    val currentMonthName = currentMonth.month.getDisplayName(
        java.time.format.TextStyle.FULL,
        java.util.Locale.getDefault()
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightGrey),
//        contentAlignment = Alignment.TopCenter
    ) {
        Calendar3(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .aspectRatio(1.3f),
            calendarInput = calendarInputList,
            onDayClick = { day ->
                clickedCalendarElem = calendarInputList.first { it.day == day }
            },
            month = currentMonthName
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
//                .align(Alignment.Center)
        ) {
            clickedCalendarElem?.toDos?.forEach {
                Text(
                    if (it.contains("Day")) it else "- $it",
//                    color = white,
                    fontWeight = FontWeight.SemiBold,
//                    fontSize = if(it.contains("Day")) 25.sp else 18.sp
                )
            }
        }
    }
}


private fun createCalendarList(totalDays: Int = 30): List<CalendarInput> {
    val calendarInputs = mutableListOf<CalendarInput>()
    for (i in 1..totalDays) {
        calendarInputs.add(
            CalendarInput(
                i, toDos = listOf(
                    "Day $i: ",
                    "2:00 pm Buying groceries",
                    "4:00 pm Meeting with Larissa"
                )
            )
        )
    }
    return calendarInputs
}

@Preview
@Composable
private fun CalendarPreview() {
//    Calendar3( calendarInput = createCalendarList(), onDayClick = {})
    CalendarBox()
}