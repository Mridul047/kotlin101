package com.mycodethesaurus.practice.basics.threads

import kotlin.concurrent.thread

fun main() {

    println("Main thread starts -> ${Thread.currentThread().name}")

    thread {
        println("Worker thread starts -> ${Thread.currentThread().name}")
        Thread.sleep(2000)
        println("Worker thread finished -> ${Thread.currentThread().name}")
    }

    println("Main thread ends -> ${Thread.currentThread().name}")
}