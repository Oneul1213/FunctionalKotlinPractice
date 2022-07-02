package chapter7.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlin.system.measureTimeMillis

sealed class CounterMsg object IncCounter: CounterMsg()
class GetCounter(val response: CompletableDeferred<Int>): CounterMsg()

@OptIn(DelicateCoroutinesApi::class, ObsoleteCoroutinesApi::class)
fun counterActor(start: Int) = GlobalScope.actor<CounterMsg> {
    var counter = start
    for (msg in channel) {
        when (msg) {
            is IncCounter -> counter++
            is GetCounter -> msg.response.complete(counter)
        }
    }
}

fun main() = runBlocking {
    val counterActor = counterActor(0)

    val time = measureTimeMillis {
        repeatInParallel(1_000_000) {
            counterActor.send(IncCounter)
        }
    }
    val counter = CompletableDeferred<Int>()
    counterActor.send(GetCounter(counter))
    println("counter = ${counter.await()}")
    println("time = $time")
}