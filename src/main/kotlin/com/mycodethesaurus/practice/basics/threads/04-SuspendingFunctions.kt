package com.mycodethesaurus.practice.basics.threads

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    println("Main thread starts -> ${Thread.currentThread().name}")

    val parentJob = CoroutineScope(Default).launch {

        launch {
            val result1 = getData1(Thread.currentThread().name)
            println("Received: $result1")
        }

        launch {
            val result2 = getData2(Thread.currentThread().name)
            println("Received: $result2")
        }

        val deferredResult = async {
            delay(4000)
            getData3(Thread.currentThread().name)
        }

        println("Received deferredResult: ${deferredResult.await()}")
    }

    runBlocking {
        parentJob.join()
    }

    println("Main thread ends -> ${Thread.currentThread().name}")
}

private suspend fun getData1(threadName: String): String {
    println("Worker1 Thread starts: $threadName")
    delay(2000)
    println("Worker1 Thread ends: $threadName")
    return "Data1 from $threadName"
}

private suspend fun getData2(threadName: String): String {
    println("Worker2 Thread starts: $threadName")
    delay(2000)
    println("Worker2 Thread ends: $threadName")
    return "Data2 from $threadName"
}

private suspend fun getData3(threadName: String): String {
    println("Worker3 Thread starts: $threadName")
    delay(2000)
    println("Worker3 Thread ends: $threadName")
    return "Data2 from $threadName"
}