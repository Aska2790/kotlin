package com.aska.development.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private fun main() = runBlocking {
    println("Flow operators...")

    println("map{...}")
    val mappedFlow = flowProvider().map {
        "mapped item $it"
    }
    flowConsumer(mappedFlow)

    println("transform{...}")
    val transformFlow = flowProvider().transform {
        emit("first emit  $it")
        emit("second emit  ${it + 4}")
    }
    flowConsumer(transformFlow)

    println("take{...}")
    val sizeLimitedFlow = flowProvider().take(2)
    flowConsumer(sizeLimitedFlow)

    println("buffer{...}")
    val bufferedFlow = flow {
        for (i in 0..3) {
            delay(500)
            emit(i)
        }}
        .buffer()

    val time = measureTimeMillis {
        bufferedFlow.collect {
            println(it)
            delay(500)
        }
    }
    println(time)

    println("conflate{...}")
    flow {
        for (i in 0..3) {
            delay(100)
            emit(i)
        }}
        .conflate()
        .collect {
            delay(300)
            println(it)
        }

}

private fun flowProvider(): Flow<Int> = flow {
    for (item in 0..3) {
        delay(100)
        emit(item)
    }
}

private suspend fun flowConsumer(flow: Flow<Any>) {
    flow.collect {
        println(it)
    }
}