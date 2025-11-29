package com.example.gmwrokouttimer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Message(val author: String, val body: String)

@Composable
fun MessageList(messages: List<Message>) {
    // LazyColumn is used for a scrollable, vertical list
    LazyColumn(modifier = Modifier.padding(4.dp)) {
        // The 'items' function iterates through your list and creates a composable for each item
        items(messages, key = { message -> message.author + message.body }) { message ->
            // A custom composable for each item in the list
            MessageCard(msg = message)
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    Card(modifier = Modifier
        .padding(vertical = 4.dp, horizontal = 6.dp)
        .fillMaxWidth()) { // Make sure you import fillMaxWidth
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Author: ${msg.author}")
            Text(text = msg.body)
        }
    }
}