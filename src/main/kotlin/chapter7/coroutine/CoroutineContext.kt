package chapter7.coroutine

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("coroutineContext 블록 실행 = $coroutineContext")
    println("coroutineContext[Job] = ${coroutineContext[Job]}")
    println(Thread.currentThread().name)
    println("-----")

    val jobs = listOf(
        launch {
            println("launch coroutineContext = $coroutineContext")
            println("coroutineContext[Job] = ${coroutineContext[Job]}")
            println(Thread.currentThread().name)
            println("-----")
        },
        async {
            println("async coroutineContext = $coroutineContext")
            println("coroutineContext[Job] = ${coroutineContext[Job]}")
            println(Thread.currentThread().name)
            println("-----")
        },
        launch(Dispatchers.Default) {   // launch(CommonPool) {
            println("common launch coroutineContext = $coroutineContext")
            println("coroutineContext[Job] = ${coroutineContext[Job]}")
            println(Thread.currentThread().name)
            println("-----")
        },
        launch(coroutineContext) {
            println("inherit launch coroutineContext = $coroutineContext")
            println("coroutineContext[Job] = ${coroutineContext[Job]}")
            println(Thread.currentThread().name)
            println("-----")
        }
    )

    jobs.forEach { job ->
        println("job = $job")
        job.join()
    }
}