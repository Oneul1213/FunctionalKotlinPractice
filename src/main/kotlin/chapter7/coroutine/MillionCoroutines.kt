package chapter7.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        repeat(1_000_000) {
            launch(coroutineContext) {
                delay(1000)
                println('.')
            }
        }
    }

    job.join()
}