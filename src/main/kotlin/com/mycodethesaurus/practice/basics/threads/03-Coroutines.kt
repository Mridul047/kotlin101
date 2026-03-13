package com.mycodethesaurus.practice.basics.threads

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {

    println(
        "Main thread starts -> ${Thread.currentThread().name} -> ${
            LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }"
    )

    GlobalScope.launch {
        println(
            "Worker thread with Global Scope starts -> ${Thread.currentThread().name} -> ${
                LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            }"
        )
        delay(2000)
        println(
            "Worker thread with Global Scope finished -> ${Thread.currentThread().name} -> ${
                LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            }"
        )
    }

    val parentJob = CoroutineScope(Default).launch {
        println(
            "Worker thread with Default Scope starts -> ${Thread.currentThread().name} -> ${
                LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            }"
        )
        delay(2000)
        println(
            "Worker thread with Default Scope finished -> ${Thread.currentThread().name} -> ${
                LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            }"
        )
    }

    runBlocking {
        parentJob.join() // Wait for the worker coroutine to finish
    }

    println(
        "Main thread ends -> ${Thread.currentThread().name} -> ${
            LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }"
    )
}