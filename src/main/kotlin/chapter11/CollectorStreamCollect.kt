package chapter11

import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.streams.asStream

fun main() {
    // toCollection
    println("--toCollection--")
    val resultantSet = (0..10).asSequence().asStream()
        .collect(Collectors.toCollection { LinkedHashSet<Int>() })
    println("resultantSet $resultantSet")

    // toMap
    println("--toMap--")
    val resultantMap = (0..10).asSequence().asStream()
        .collect(Collectors.toMap<Int, Int, Int>({
            it
        }, {
            it*it
        }))
    println("resultantMap = $resultantMap")

    // joining
    println("--joining--")
    val resultantString = Stream.builder<String>()
        .add("아이템 1")
        .add("아이템 2")
        .add("아이템 3")
        .add("아이템 4")
        .add("아이템 5")
        .add("아이템 6")
        .build()
        .collect(Collectors.joining(" - ", "여기서 시작함=>", "<=여기서 끝남"))
    println("resultantString $resultantString")

    // groupingBy
    println("--groupingBy--")
    val resultantMapList = (1..20).asSequence().asStream()
        .collect(Collectors.groupingBy<Int, Int> { it%5 })
    println("resultantMapList $resultantMapList")
}