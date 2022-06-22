package chapter7

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val jobs = List(1_000_000) {
        launch {
            delay(1_000)
            print('.')
        }
    }
    jobs.forEach { job -> job.join() }
}