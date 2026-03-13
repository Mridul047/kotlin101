package com.mycodethesaurus.practice.basics.threads

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun main() {

    println(
        "Main thread starts -> ${Thread.currentThread().name} -> ${
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }"
    )

    val parentJob = CoroutineScope(Dispatchers.Default).launch(exceptionHandler) {
        // Without supervisor — one child coroutine failure kills all sibling coroutines

        supervisorScope {

            val task1 = launch {
                println("Doing primary work.")
                throw Exception("Exception while doing primary work")
            }

            try {
                val task2 =
                    withTimeout(1000) {
                        repeat(2) {
                            println("Doing secondary work..$it")
                            delay(2000)
                        }
                    }
            } catch (e: Exception) {
                println("Exception in Task2 -> ${e.message}")
            }

            val task3 = withTimeoutOrNull(6000) {
                repeat(2) {
                    println("Doing miscellaneous work...$it")
                    delay(2000)
                }
                "Task3 Completed with success"
            }

            if (task3 == null) println("Task3 failed due to timeout")
            else println("Task3 : $task3")

            val task4 = launch {
                println("Doing IO work....")
                throw Exception("Exception while doing IO work")
            }
        }
    }

    parentJob.invokeOnCompletion {
        it?.let {
            println("Parent Task FAILED -> ${it.message}")
        } ?: println("Parent Task COMPLETED")
    }

    runBlocking {
        parentJob.join()
    }

    println(
        "Main thread ends -> ${Thread.currentThread().name} -> ${
            LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }"
    )
}

// Use this to create exception handler for root coroutine
val exceptionHandler = CoroutineExceptionHandler { _, exception ->
    println("CoroutineExceptionHandler got an exception: ${exception.message}")
}