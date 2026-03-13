package com.mycodethesaurus.practice.basics.threads

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    println(
        "Main thread starts -> ${Thread.currentThread().name} -> ${
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }"
    )

    val parentJob = CoroutineScope(Dispatchers.Default).launch {

        val job1 = launch {
            try {
                val result = getData1(Thread.currentThread().name)
                println("Result1: $result")
            } catch (e: Exception) {
                println("Exception: $e")
                throw e
            }
        }

        job1.cancel(CancellationException("Job1 was cancelled"));
        job1.join()

        val job2 = launch {
            val result = getData2(Thread.currentThread().name)
            println("Result2: $result")
        }

        val job3 = launch {
            repeat(2) {
                println("Working...$it")
                delay(1000)
            }
        }
    }

    parentJob.invokeOnCompletion {
        it?.let {
            println("Parent Job FAILED -> ${it.message}")
        } ?: println("Parent Job COMPLETED")
    }

    runBlocking {
        parentJob.join()
    }

    println(
        "Main thread ends -> ${Thread.currentThread().name} -> ${
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }"
    )
}

private suspend fun getData1(threadName: String): String {
    println("Worker1 Thread starts: $threadName")
    delay(2000)
    println("Worker1 Thread ends: $threadName")
    return "Data1 from $threadName"
}

private suspend fun getData2(threadName: String): String {
    println("Worker2 Thread starts: $threadName")
    delay(2500)
    println("Worker2 Thread ends: $threadName")
    return "Data2 from $threadName"
}