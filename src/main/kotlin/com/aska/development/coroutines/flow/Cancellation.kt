package com.aska.development.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

private fun main() = runBlocking<Unit> {
    println("Flow cancellation...")
    println("withTimeoutOrNull(..)")
    val flow = flowProvider()
    withTimeoutOrNull(250){
        flowConsumer(flow)
    }

}

private fun flowProvider(): Flow<Int> = flow {
    for (item in 0..3) {
        delay(100)
        emit(item)
    }
}

private suspend fun flowConsumer(flow:Flow<Int>){
    flow.collect {
        println(it)
    }
}