package chapter11

import java.util.stream.Collectors
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
}