package chapter3

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MyDataImmutable {
    val someData: Int = 0
}

fun main() = runBlocking<Unit> {
    val myData: MyDataImmutable = MyDataImmutable()

    async {
        var someDataCopy = myData.someData
        for (i in 11..20) {
            someDataCopy += i
            println("1번째 async에서의 someData $someDataCopy")
            delay(500)
        }
    }

    async {
        var someDataCopy = myData.someData
        for (i in 1..10) {
            someDataCopy++
            println("2번째 async에서의 someData $someDataCopy")
            delay(300)
        }
    }

    runBlocking { delay(10000) }
}