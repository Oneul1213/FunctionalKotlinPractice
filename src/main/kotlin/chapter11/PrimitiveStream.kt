package chapter11

import java.util.stream.DoubleStream
import java.util.stream.IntStream

fun main() {
    val intStream = IntStream.range(1, 10)
    val result = intStream.sum()
    println("요소의 합계는 $result 이다")

    val doubleStream = DoubleStream.iterate(1.5) { item -> item*1.3 }
    val avg = doubleStream
        .limit(10)
        .peek {
            println("아이템 $it")
        }.average()
    println("10개 아이템의 평균 $avg")
}