package com.example.gmwrokouttimer.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

// # Copy any file to current app cache 'cache/copied_file.dat'
@Composable
fun FileCopyExample() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var fileContent by remember { mutableStateOf("Select a file") }

    val pickFileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            scope.launch(Dispatchers.IO) {
                // 1. Open InputStream from URI
                context.contentResolver.openInputStream(it)?.use { inputStream ->
                    // 2. Create target file in cache
//                    val targetFile = File(context.cacheDir, "copied_file.dat")
                    val targetFile = File(context.filesDir, "copied_filed.dat")

//                    FileOutputStream(targetFile).use { outputStream ->
//                        // 3. Copy the stream
//                        inputStream.copyTo(outputStream)
//                    }
//                    fileContent = "Copied to: ${targetFile.absolutePath}"

                    targetFile.outputStream().use{ outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                    fileContent = "Copied to: ${targetFile.absolutePath}"
                }
            }
        }
    }

    Button(onClick = { pickFileLauncher.launch("*/*") }) {
        Text(fileContent)
    }
}
