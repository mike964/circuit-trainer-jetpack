package com.example.gmwrokouttimer.presentation.progress

import kotlin.time.Duration.Companion.seconds
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.gmwrokouttimer.presentation.AppViewModel
import com.example.gmwrokouttimer.presentation.NoteViewModel
import com.example.gmwrokouttimer.presentation.progress.calendar.CalendarView
import com.example.gmwrokouttimer.utils.formatDateString
import java.time.LocalDate
import java.util.Date

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProgressScreen(appVm: AppViewModel, navController: NavController, noteVm: NoteViewModel) {
//    val activities = appVm.latestActivity
    val activities by noteVm.activities.collectAsStateWithLifecycle()

    val localDate = LocalDate.now()

    val currentYear = localDate.year   // 2026 :Int
    val currentMonth = localDate.month   // "April" :String
    val previousMonthDate = localDate.minusMonths(1)

    val currentMonthName = currentMonth.getDisplayName(
        java.time.format.TextStyle.FULL,
        java.util.Locale.getDefault()
    )

    fun monthNameString(ld: LocalDate): String {
        return ld.month.getDisplayName(
            java.time.format.TextStyle.FULL,
            java.util.Locale.getDefault()
        )
    }

    var selectedMonth by remember { mutableStateOf(localDate.withDayOfMonth(1)) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Text("Progress Screen")
            Button(onClick = {
                // Go back to the previous screen (Home)
                navController.popBackStack()
            }) {
                Text("Go Back")
            }
        }

        Text("Active days table")
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
            Text("$selectedMonth")
            IconButton(onClick = {
                selectedMonth = selectedMonth.plusMonths(1)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Next month"
                )
            }
        }

        Text("Previous month : ${previousMonthDate.month}")
        Text("Selected month : ${monthNameString(selectedMonth)}")

        Calendar2(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.LightGray), ld = selectedMonth
        )

//        Box(
//            modifier = Modifier
//                .background(Color.LightGray) // Sets a solid red background
//        ) {
//            CalendarView(
//                month = Date() ,
//                date = List(30) {
//                    Pair(Date(), false)
//                },
//                displayNext = true,
//                displayPrev = true,
//                onClickNext = { /*TODO*/ },
//                onClickPrev = { /*TODO*/ },
//                onClick = { /*TODO*/ },
//                startFromSunday = true,
//            )
//        }

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
fun ActivityListItem(activity: com.example.gmwrokouttimer.database.model.Activity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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