package com.example.gmwrokouttimer.presentation

import kotlinx.coroutines.*

fun main () = runBlocking {
    println("Hello!")
    launch {
        delay(1000L)
        println("Welcome.")
    }
    println("Bitch")
}