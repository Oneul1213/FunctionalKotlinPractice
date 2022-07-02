package chapter7.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

suspend fun repeatInParallel(times: Int, block: suspend () -> Unit) = coroutineScope {
    val job = launch {
        repeat(times) {
            launch(coroutineContext) {
                block()
            }
        }
    }

    job.join()
}

fun main() = runBlocking {
    var counter = 0
    val time = measureTimeMillis {
        repeatInParallel(1_000_000) {
            counter++
        }
    }
    println("counter = $counter")
    println("time = $time")
}