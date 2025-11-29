package com.example.gmwrokouttimer

import kotlinx.coroutines.*

fun main () = runBlocking {
    println("Hello!")
    launch {
        delay(1000L)
        println("Welcome.")
    }
    println("Bitch")
}