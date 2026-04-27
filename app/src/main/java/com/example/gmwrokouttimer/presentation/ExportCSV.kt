package com.example.gmwrokouttimer.presentation

import android.content.Context
import android.net.Uri
import android.os.Environment
import com.opencsv.CSVWriter
import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.StatefulBeanToCsvBuilder
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
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