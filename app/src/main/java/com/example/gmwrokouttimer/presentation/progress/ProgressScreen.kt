package com.example.gmwrokouttimer.presentation.progress

import kotlin.time.Duration.Companion.seconds
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.gmwrokouttimer.database.model.Activity
import com.example.gmwrokouttimer.presentation.AppViewModel
import com.example.gmwrokouttimer.presentation.NoteViewModel
import com.example.gmwrokouttimer.utils.formatDate
import com.example.gmwrokouttimer.utils.formatDateString
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProgressScreen(appVm: AppViewModel, navController: NavController, noteVm: NoteViewModel) {

    val localDate = LocalDate.now()
    var selectedMonth by remember { mutableStateOf(localDate.withDayOfMonth(1)) }

    val selectedMonthFirstDay = selectedMonth.withDayOfMonth(1)
    val selectedMonthLastDay = selectedMonth.withDayOfMonth(selectedMonth.lengthOfMonth())
//    val activities = appVm.latestActivity
    val activities by noteVm.activities.collectAsStateWithLifecycle()
    // # Get activities from db in date period
    var activitiesInTimePeriod by remember { mutableStateOf(emptyList<Activity>()) }
    var showPopup by remember { mutableStateOf(false) }  // Add new activity to db

    // Restarts every time key changes - Filter activities by date in UI
    LaunchedEffect(activities) {
        activitiesInTimePeriod = activities.filter {
            val date = Instant.parse("${it.dateTime.slice(0..19)}Z").atZone(ZoneId.systemDefault())
                .toLocalDate()
            date in selectedMonthFirstDay..selectedMonthLastDay
        }
    }
    LaunchedEffect(selectedMonth) {
        activitiesInTimePeriod = activities.filter {
            val date = Instant.parse("${it.dateTime.slice(0..19)}Z").atZone(ZoneId.systemDefault())
                .toLocalDate()
            date in selectedMonthFirstDay..selectedMonthLastDay
        }
    }

//    val currentYear = localDate.year   // 2026 :Int
//    val currentMonth = localDate.month   // "April" :String

    Log.d("Progress84", "Selected month : ${selectedMonth.month}")
//    Log.d("Progress84", "First day of selected month : $selectedMonthFirstDay") // 2026-04-01
//    Log.d("Progress84", "Last day of selected month : $selectedMonthLastDay")   // 2026-04-30

    Log.d("Progress", activitiesInTimePeriod.size.toString())

    val selectedMonthActivityDays = activitiesInTimePeriod.map { activity ->
        val date =
            Instant.parse("${activity.dateTime.slice(0..19)}Z").atZone(ZoneId.systemDefault())
                .toLocalDate()
        date.dayOfMonth
    }
//    data class DayWithActivity(val day: Int, val hasActivity: Boolean, val color: Color)

    val activeDaysCounts = selectedMonthActivityDays.groupingBy { it }.eachCount()
    Log.d("Progress102", selectedMonthActivityDays.toString())
    Log.d("Progress102", activeDaysCounts.toString())

//    Log.d("Progress102", selectedMonthActivityDays.toString())  //  [19, 14, 14, 13]

    if (showPopup) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = { showPopup = false }
        ) {
            // Content of the popup
            Box(
                modifier = Modifier
                    .width(380.dp)
                    .height(540.dp)
                    .dropShadow(
                        shape = RoundedCornerShape(12.dp),
                        shadow = Shadow(
                            radius = 8.dp,
                            spread = 3.dp,
                            color = Color(0x40000000),
                            offset = DpOffset(x = 1.dp, 1.dp)
                        )
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("This is a basic popup", Modifier.align(Alignment.Center))
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier.weight(1f)
            ) {
                Text(
                    text = "< Back",
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable {
                            // Go back to the previous screen (Home)
                            navController.popBackStack()
                        }
                )
            }
            Column(Modifier.weight(3f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Active Days",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
            Column(
                Modifier.weight(1f)
//                .background(Color.Yellow)
                , horizontalAlignment = Alignment.End
            ) {
                IconButton(onClick = {
                    showPopup = true
                }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        tint = Color.Blue,
                        contentDescription = "Add new item"
                    )
                }
            }
        }
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround, // Spacing behavior
            verticalAlignment = Alignment.CenterVertically    // Vertical position
        ) {
            IconButton(onClick = {
                selectedMonth = selectedMonth.minusMonths(1)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Prev month"
                )
            }
            Text(formatDate(selectedMonth, "MMMM yyyy"))
            IconButton(onClick = {
                selectedMonth = selectedMonth.plusMonths(1)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Next month"
                )
            }
        }

        Calendar2(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(32.dp, 8.dp)
                .background(Color.DarkGray),
//            ld = selectedMonth,
//            highlightedDays = listOf(1, 5, 10, 20, 25)
            highlightedDays = selectedMonthActivityDays
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Latest activity")
        LazyColumn {
            items(activities, key = { it.id }) {
                ActivityListItem(it)
            }
        }
    }
}

@Composable
fun ActivityListItem(activity: Activity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Set background color here
//        contentColor = Color.Black       // Optional: Set default text/icon color
        ),
//        onClick = onClick
    ) {
        Row(
            Modifier.padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(3f)
            ) {
//            Text(text = activity.id.toString())
                Text(text = activity.title)

            }
            Column(
                modifier = Modifier
                    .weight(2f)
            ) {
                Text(text = formatSeconds(activity.duration.toLong()))
//                Text(text = "Rate : ${activity.rate}")
            }
            Column(Modifier.weight(2f)) {
                Text(text = formatDateString(activity.dateTime, "MM/dd/YYYY"))
            }
        }
        Row(modifier = Modifier.padding(12.dp, 8.dp)) {
            Text("Note : ${activity.note}")
        }
    }
}

@SuppressLint("DefaultLocale")
fun formatSeconds(totalSeconds: Long): String {
    return totalSeconds.seconds.toComponents { hours, minutes, seconds, _ ->
        // Formats as HH:MM:SS
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}

/*
Instead of hardcoding colors like Color.Red, use your
 theme's color scheme (e.g., MaterialTheme.colorScheme.surface)
 to ensure support for both Light and Dark modes.
 */