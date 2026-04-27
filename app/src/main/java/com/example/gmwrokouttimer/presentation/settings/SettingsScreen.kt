package com.example.gmwrokouttimer.presentation.settings

import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.gmwrokouttimer.presentation.CountdownViewModel
import com.example.gmwrokouttimer.presentation.HorizontalNumberPicker
import com.example.gmwrokouttimer.presentation.NoteViewModel
import com.example.gmwrokouttimer.presentation.exportToCsv
import com.example.gmwrokouttimer.utils.formatMilliseconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(timerVm: CountdownViewModel, navController: NavController, noteVm: NoteViewModel) {
    val timerState by timerVm.uiState.collectAsState()
    val totalTimeLeft by timerVm.totalTimeLeft.collectAsState()
    val activities by noteVm.activities.collectAsStateWithLifecycle()


    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // 1. Define the File Picker Launcher
    val exportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/octet-stream"),
    ) { uri ->
        uri?.let { destinationUri ->
            // 2. Perform the copy in a Coroutine (IO Dispatcher)
            scope.launch(Dispatchers.IO) {
                val dbFile = context.getDatabasePath("your_db_name")
                context.contentResolver.openOutputStream(destinationUri)?.use { output ->
                    dbFile.inputStream().use { input ->
                        input.copyTo(output)
                    }
                }

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
                Text("Settings", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            }
            Column(Modifier.weight(1f)) { }
        }
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        Column {
            Text("Set workout circuit timing")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Work time (Seconds)")
            HorizontalNumberPicker(
                default = 1,
                displayNumber = timerState.workTimeSeconds,
                min = 2, max = 20,
                height = 30.dp
            ) {
                timerVm.setWorkTime(it)
            }
            Text("Rest time (Seconds)")
            HorizontalNumberPicker(
                default = 1,
                displayNumber = timerState.workTimeSeconds,
                min = 2, max = 20,
                height = 30.dp
            ) {
                // # Same as work seconds picker
            }
            Text("Rounds")
            HorizontalNumberPicker(default = timerState.initRounds, height = 30.dp) {
                timerVm.setInitRounds(it)
            }
//            Text(   "Total time : "   + formatSeconds((totalTime).toLong())   )

            Spacer(modifier = Modifier.height(8.dp))

            // # Total workout time in MM:SS
            Text("Total : ${formatMilliseconds(totalTimeLeft)}")

            Spacer(modifier = Modifier.height(8.dp))

            Text("Backup your data - export to CSV")
            Text("Import data - import from CSV")

//            FileCopyExample()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.CreateDocument("text/csv")
            ) { uri ->
                uri?.let { exportToCsv(context, it, activities.map{ it.toCSV() }) }
            }

            Button(onClick = {
                val fileName = "gmtrainer-activities-${System.currentTimeMillis()}"
                val data = activities.map{ it.toCSV() }
                Log.d("xx", data.toString())
                launcher.launch("$fileName.csv")
            }) {
                Text("Export Room DB to CSV")
            }


            val fileName = "my_file2.txt"
            val fileContent = "Hello, Compose!"
//            Button(onClick = {
//                context.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
//                    output.write(fileContent.toByteArray())
//                }
//            }) {
//                Text("Save File")
//            }

            val launcher2 = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.CreateDocument("text/plain")
            ) { uri ->
                uri?.let {
                    context.contentResolver.openOutputStream(it)?.use { outputStream ->
                        outputStream.write(fileContent.toByteArray())
                    }
                }
            }

            Button(onClick = { launcher2.launch(fileName) }) {
                Text("Save to Documents")
            }
        }
    }
}
