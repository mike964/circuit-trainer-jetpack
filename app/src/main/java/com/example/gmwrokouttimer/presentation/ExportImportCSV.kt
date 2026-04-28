package com.example.gmwrokouttimer.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.gmwrokouttimer.database.model.Activity
import com.opencsv.CSVReader
import com.opencsv.CSVWriter
import com.opencsv.bean.CsvBindByName
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

//data class TransactionCSV(
//    @CsvBindByName(column = "ID") val id: Int,
//    @CsvBindByName(column = "Title") val title: String,
//    @CsvBindByName(column = "Amount") val amount: Double
//)

data class ActivityCSV(
    @CsvBindByName(column = "ID") val id: Int,
    @CsvBindByName(column = "Title") val title: String,
    @CsvBindByName(column = "Note") val note: String,
    @CsvBindByName(column = "DateTime") val dateTime: String,
    @CsvBindByName(column = "Duration") val duration: Int,
    @CsvBindByName(column = "Calories") val calories: Int,
    @CsvBindByName(column = "Rate") val rate: Int,
    @CsvBindByName(column = "PresetId") val workoutPresetId: Int,
)

fun exportToCsv(context: Context, uri: Uri, dataList: List<ActivityCSV>) {
    try {
        context.contentResolver.openOutputStream(uri)?.use { outputStream ->
            val writer = BufferedWriter(OutputStreamWriter(outputStream))
            val csvWriter = CSVWriter(writer)
            // Add Header row
            csvWriter.writeNext(
                arrayOf(
                    "ID",
                    "Title",
                    "Note",
                    "DateTime",
                    "Duration",
                    "Calories",
                    "Rate",
                    "PresetId"
                )
            )

            // Add data rows
            dataList.forEach {
                csvWriter.writeNext(
                    arrayOf(
                        it.id.toString(),
                        it.title,
                        it.note,
                        it.dateTime,
                        it.duration.toString(),
                        it.calories.toString(),
                        it.rate.toString(),
                        it.workoutPresetId.toString()
                    )
                )
            }
            writer.close()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun importFromCsv(context: Context, uri: Uri, viewModel: NoteViewModel) {
    try {
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            val reader = CSVReader(InputStreamReader(inputStream))
            val allRows = reader.readAll()
            if (allRows.isEmpty()) return

            val activities = allRows.drop(1).mapNotNull { tokens ->
                if (tokens.size >= 8) {
                    Activity(
                        title = tokens[1],
                        note = tokens[2],
                        dateTime = tokens[3],
                        duration = tokens[4].toIntOrNull() ?: 0,
                        calories = tokens[5].toIntOrNull() ?: 0,
                        rate = tokens[6].toIntOrNull() ?: 0,
                        workoutPresetId = tokens[7].toIntOrNull(),
                        imageId = null,
                        city = "",
                        country = "",
                        location = null
                    )
                } else null
            }

            if (activities.isNotEmpty()) {
                viewModel.insertActivities(activities)
                Log.d("xx-99", activities[0].toString())
                Log.d("xx-99", activities[1].toString())
                Log.d("CSV_IMPORT", "Successfully imported ${activities.size} activities")
            }
        }
    } catch (e: Exception) {
        Log.e("CSV_IMPORT", "Error importing CSV", e)
    }
}