package com.example.gmwrokouttimer.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.gmwrokouttimer.database.model.Activity
import com.opencsv.CSVWriter
import com.opencsv.bean.CsvBindByName
import java.io.BufferedWriter
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

fun exportToCsv(context: Context, uri : Uri, dataList: List<ActivityCSV>) {
    val fileName = "activities"
    // Save to the app's internal documents directory
//    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName)


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
    context.contentResolver.openInputStream(uri)?.use { inputStream ->
        val reader = inputStream.bufferedReader()
        // Skip header and parse rows
        val activities = reader.lineSequence()
            .drop(1)
            .map { line ->
                val tokens = line.split(",")
                Activity(title = tokens[0], note = tokens[1], dateTime = tokens[2], duration = tokens[3].toInt(), calories = tokens[4].toInt(), rate = tokens[5].toInt(), workoutPresetId = tokens[6].toInt(), imageId = null, city = "", country = "", location = null)
            }.toList()

        Log.d("xx", activities.toString())
//        viewModel.insertActivities(activities)
    }
}