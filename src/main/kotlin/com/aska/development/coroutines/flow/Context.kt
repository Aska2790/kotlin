package com.aska.development.coroutines.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private fun main() = runBlocking {

    println("Flow context...")
    println("Running - ${Thread.currentThread().name} thread")

    println("WithContext()")
    val flow = (1..3).asFlow()

    withContext(Dispatchers.Default) {
        println(Thread.currentThread().name)
        flowConsumer(flow)
    }


    // Throw exception
//    println("Generate different context()")
//    val buildFlow = flow<Int> {
//        withContext(Dispatchers.Default) {
//            emit(1)
//            emit(2)
//            emit(3)
//        }
//    }
//    flowConsumer(buildFlow)

    println("Change context")
    val buildFlow = flow<Int> {
        println(Thread.currentThread().name)
        emit(1)
        delay(100)
        emit(2)
        delay(100)
        emit(3)
        delay(100)

    }.flowOn(Dispatchers.Default)
    flowConsumer(buildFlow)
}

private suspend fun flowConsumer(flow: Flow<Int>) {
    println()
    flow.collect {
        println("Collect ${Thread.currentThread().name} + $it")
    }
}

