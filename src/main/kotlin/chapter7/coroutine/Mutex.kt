package chapter7.coroutine

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val mutex = Mutex()
    var counter = 0

    val time = measureTimeMillis {
        repeatInParallel(1_000_000) {
            mutex.withLock {
                counter++
            }
        }
    }
    println("counter = $counter")
    println("time = $time")
}