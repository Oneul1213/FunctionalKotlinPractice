package chapter7.coroutine

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val result = CompletableDeferred<String>()

    val world = launch {
        delay(500)
        result.complete("World (또 다른 코루틴에서)")
    }

    val hello = launch {
        println("Hello ${result.await()}")
    }

    hello.join()
    world.join()
}