package chapter11

import java.util.stream.Collectors
import kotlin.streams.asStream

fun main() {
    val resultantSet = (0..10).asSequence().asStream()
        .collect(Collectors.toCollection { LinkedHashSet<Int>() })
    println("resultantSet $resultantSet")
}