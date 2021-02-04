package com.aska.development.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

private fun main() = runBlocking {
    println("Flow builders...")

    println("flow{...}")
    var flow = flow {
        for (item in 0..3) {
            delay(100)
            emit(item)
        }
    }
    flowConsumer(flow)

    println("asFlow()")
    flow = (0..3).asFlow()
    flowConsumer(flow)

    println("flowOf()")
    flow = flowOf(0, 1, 2, 3)
    flowConsumer(flow)

}


private suspend fun flowConsumer(flow: Flow<Int>) {
    flow.collect { value ->
        println(value)
    }
}
