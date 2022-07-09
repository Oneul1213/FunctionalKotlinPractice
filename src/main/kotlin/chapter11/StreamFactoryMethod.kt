package chapter11

import java.util.stream.Collectors
import java.util.stream.Stream

fun main() {
    // 스트림 빌더
    println("--스트림 빌더--")

    val stream = Stream.builder<String>()
        .add("아이템 1")
        .add("아이템 2")
        .add("아이템 3")
        .add("아이템 4")
        .add("아이템 5")
        .add("아이템 6")
        .add("아이템 7")
        .add("아이템 8")
        .add("아이템 9")
        .add("아이템 10")
        .build()
    println("스트림 ${stream.collect(Collectors.toList())}")

    // Stream.empty()
    println("--Stream.empty()--")

    val emptyStream = Stream.empty<String>()
    val item = emptyStream.findAny()
    println("아이템 $item")
}