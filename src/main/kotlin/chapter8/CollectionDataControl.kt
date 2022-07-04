package chapter8

import kotlin.math.roundToInt
import kotlin.math.sqrt

fun main() {
    // map
    println("--map--")
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val modifiedList = list.map { it * 2 }

    println("modifiedList -> $modifiedList")

    println()

    // filter
    println("--filter--")
    val list2 = 1.until(50).toList()
    val filteredListEven = list2.filter { it%2 == 0 }

    println("filteredListEven -> $filteredListEven")

    val filteredListPSquare = list2.filter {
        val sqroot = sqrt(it.toDouble()).roundToInt()
        sqroot * sqroot == it
    }

    println("filteredListPSquare -> $filteredListPSquare")

    println()

    // flatMap
    println("--flatMap--")
    val list3 = listOf(10, 20, 30)
    val flatMappedList = list3.flatMap {
        it.rangeTo(it+2).toList()
    }
    println("flatMappedList -> $flatMappedList")

    println()

    // drop
    println("--drop--")
    val list4 = 1.until(50).toList()
    println("list.drop(25) -> ${list4.drop(25)}")
    println("list.dropLast(25) -> ${list4.dropLast(25)}")

    println()

    // take
    println("--take--")
    val list5 = 1.until(50).toList()
    println("list.take(25) -> ${list5.take(25)}")
    println("list.takeLast(25) -> ${list5.takeLast(25)}")
    println("list.takeWhile { it <= 10 } -> ${list5.takeWhile { it <= 10 }}")
    println("list.takeLastWhile { it >= 40 } -> ${list5.takeLastWhile { it >= 40 }}")

    println()

    // zip
    println("--zip--")
    val list6 = listOf(1, 2, 3, 4, 5)
    val list7 = listOf(
        "아이템 1",
        "아이템 2",
        "아이템 3",
        "아이템 4",
        "아이템 5"
    )
    val resultantList = list6.zip(list7)

    println(resultantList)

    println()

    val list8 = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val list9 = listOf(
        "아이템 1",
        "아이템 2",
        "아이템 3",
        "아이템 4",
        "아이템 5"
    )

    println("list8.zip(list9) -> ${list8.zip(list9)}")
    println("list8.zipWithNext() -> ${list8.zipWithNext()}")

    println()
}