package chapter7.coroutine

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    val channel = Channel<String>()

    val world = launch {
        delay(500)
        channel.send("World (채널을 사용한 또 다른 코루틴에서)")
    }

    val hello = launch {
        println("Hello ${channel.receive()}")
    }

    hello.join()
    world.join()
}