package chapter7

import java.util.concurrent.Executors

fun main() {
    val executor = Executors.newFixedThreadPool(1024)
    repeat(10000) {
        executor.submit {
            Thread.sleep(1000)
            print('.')
        }
    }
    executor.shutdown()
}