package chapter7

import kotlin.concurrent.thread

fun main() {
    val computation = thread {
        Thread.sleep(1000)
        println("World!")
    }
    println("Hello ")
    computation.join()
}