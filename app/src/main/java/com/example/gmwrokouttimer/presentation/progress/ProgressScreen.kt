package com.example.gmwrokouttimer.presentation.progress

import android.R
import kotlin.time.Duration.Companion.seconds
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.gmwrokouttimer.database.model.Activity
import com.example.gmwrokouttimer.presentation.AppViewModel
import com.example.gmwrokouttimer.presentation.NoteViewModel
import com.example.gmwrokouttimer.utils.formatDate
import com.example.gmwrokouttimer.utils.formatDateString
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProgressScreen(appVm: AppViewModel, navController: NavController, noteVm: NoteViewModel) {
//    val activities = appVm.latestActivity
    val activities by noteVm.activities.collectAsStateWithLifecycle()
    // # Get activities from db in date period
    val activitiesInTimePeriod by noteVm.getNotesByDate(0, 0).collectAsStateWithLifecycle()


    val localDate = LocalDate.now()

    val currentYear = localDate.year   // 2026 :Int
    val currentMonth = localDate.month   // "April" :String
    val previousMonthDate = localDate.minusMonths(1)

    val currentMonthName = currentMonth.getDisplayName(
        TextStyle.FULL,
        Locale.getDefault()
    )

    fun monthNameString(ld: LocalDate): String {
        return ld.month.getDisplayName(  // March
            TextStyle.FULL,
            Locale.getDefault()
        )
    }

    var selectedMonth by remember { mutableStateOf(localDate.withDayOfMonth(1)) }

    val highlightedDaysForSelectedMonth = listOf(1, 5, 10, 20, 25)
    Log.d("Progress", activitiesInTimePeriod.toString())


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
            Column(Modifier.weight(3f) ,   horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Active Days" ,  fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            }
            Column(Modifier.weight(1f)) { }
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
//        Text("Previous month : ${previousMonthDate.month}")  // MARCH
//        Text("Selected month : ${monthNameString(selectedMonth)}")

        Calendar2(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(32.dp, 8.dp)
                .background(Color.DarkGray),
            ld = selectedMonth,
            highlightedDays = listOf(1, 5, 10, 20, 25)
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
fun ActivityListItem(activity: Activity) {
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