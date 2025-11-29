package com.example.gmwrokouttimer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Counter(
    // The viewModel() function gets an existing ViewModel instance or creates a new one
//    viewModel: AppViewModel = viewModel()
    viewModel: AppViewModel
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Counter: ${viewModel.count}")

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { viewModel.decrementCount() }) {
                Text("Decrement")
            }
            Button(onClick = { viewModel.incrementCount() }) {
                Text("Increment")
            }
        }
    }
}