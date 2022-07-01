package chapter7.coroutine

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val channel = Channel<Char>()

    val sender = launch {
        repeat(1000) {
            delay(10)
            channel.send('.')
            delay(10)
            channel.send(',')
        }
        channel.close()
    }

    for (msg in channel) {
        print(msg)
    }

    sender.join()
}