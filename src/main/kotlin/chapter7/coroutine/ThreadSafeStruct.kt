package chapter7.coroutine

import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val counter = AtomicInteger(0)
    val time = measureTimeMillis {
        repeatInParallel(1_000_000) {
            counter.incrementAndGet()
        }
    }
    println("counter = ${counter.get()}")
    println("time = $time")
}