package com.example.gmwrokouttimer.presentation.progress.calendar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.LocalDate
import java.util.Date

@Composable
fun CalendarGrid(
    date: List<Pair<Date, Boolean>>,
    onClick: (Date) -> Unit,
    startFromSunday: Boolean,
    modifier: Modifier = Modifier,
) {
    val weekdayFirstDay = formatToWeekDay()
    val weekdays = getWeekDays(startFromSunday)
    CalendarCustomLayout(modifier = modifier) {
        weekdays.forEach {
            WeekdayCell(weekday = it)
        }
        // Adds Spacers to align the first day of the month to the correct weekday
        repeat(if (!startFromSunday) weekdayFirstDay - 2 else weekdayFirstDay - 1) {
            Spacer(modifier = Modifier)
        }
        date.forEach {
            CalendarCell(date = it.first, signal = it.second, onClick = { onClick(it.first) })
        }
    }
}

private fun formatToWeekDay(): Int {
    return LocalDate.now().dayOfWeek.value
}

