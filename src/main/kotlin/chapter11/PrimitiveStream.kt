package chapter11

import java.util.stream.IntStream

fun main() {
    val intStream = IntStream.range(1, 10)
    val result = intStream.sum()
    println("요소의 합계는 $result 이다")
}