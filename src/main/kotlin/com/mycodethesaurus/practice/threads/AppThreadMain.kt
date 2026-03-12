package com.mycodethesaurus.practice.threads

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds

suspend fun greet() {
    delay(2.seconds)
    println("=== The greet() on the thread: ${Thread.currentThread().name} ===")
}

suspend fun main(args: Array<String>) {
    withContext(Dispatchers.IO) {

        launch {
            println("=== Welcome to Kodehouse on the thread: ${Thread.currentThread().name} ===")
        }

        launch {
            greet()
        }

    }
}