package com.aska.development.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

private fun main() = runBlocking {
    println("Flow terminal...")

    println("collect{...}")
    flowProvider()
        .collect {
            println(it)
        }

    println("toList{...}")
    val toList: List<Int> = flowProvider()
        .toList()
    toList.forEach {
        println(it)
    }

    println("toSet{...}")
    val toSet = flowProvider()
        .transform {
            emit(it)
            emit(it)
        }
        .toSet()

    toSet.forEach {
        println(it)
    }

    println("first()")
    val first: Int = flowProvider().first()
    println(first)

    println("single()")
    val single: Int = flowOf(1).single()
    println(single)

    println("reduce()")
    val reducedValue: Int = flowProvider().reduce { accumulator: Int, value ->
        accumulator + value
    }
    println(reducedValue)

    println("fold()")
    val foldValue: Int = flowProvider().fold(4) { accumulator: Int, value ->
        accumulator + value
    }
    println(foldValue)


    println("filter()")
    flowProvider()
        .filter {
            it % 2 == 0
        }.map {
            it.toString()
        }.collect {
            println(it)
        }

}

private fun flowProvider(): Flow<Int> = flow {
    for (item in 0..3) {
        delay(100)
        emit(item)
    }
}

