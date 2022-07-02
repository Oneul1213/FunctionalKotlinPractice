package chapter7.coroutine

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    var counter = 0

    val counterContext = newSingleThreadContext("CounterContext")

    val time = measureTimeMillis {
        repeatInParallel(1_000_000) {
            withContext(counterContext) {
                counter++
            }
        }
    }
    println("counter = $counter")
    println("time = $time")
}