package chapter7.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun dotsAndCommas(size: Int) = GlobalScope.produce {
    repeat(size) {
        delay(10)
        send('.')
        delay(10)
        send(',')
    }
}

fun main() = runBlocking {
    val channel = dotsAndCommas(1000)

    for (msg in channel) {
        print(msg)
    }
}