package chapter3

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// 불변성의 장점 - 스레드 안전성
class MyData {
    var someData: Int = 0
}

fun main() = runBlocking<Unit> {
    // 불변성의 장점 - 스레드 안전성
    val myData: MyData = MyData()

    async {
        for (i in 11..20) {
            myData.someData += i
            println("1번째 async로부터의 someData ${myData.someData}")
            delay(500)
        }
    }

    async {
        for (i in 1..10) {
            myData.someData++
            println("2번째 async로부터의 someData ${myData.someData}")
            delay(300)
        }
    }

    runBlocking { delay(10000) }
}