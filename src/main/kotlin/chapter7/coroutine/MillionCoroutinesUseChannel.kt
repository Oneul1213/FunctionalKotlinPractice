package chapter7.coroutine

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val channel = Channel<Char>()

    val jobs = List(1_000_000) {
        launch {
            delay(1000)
            channel.send('.')
        }
    }
    repeat(1_000_000) { println(channel.receive()) }
    jobs.forEach { job -> job.join() }
}