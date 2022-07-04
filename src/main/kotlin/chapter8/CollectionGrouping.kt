package chapter8

fun main() {
    val list = 1.rangeTo(50).toList()
    println(list.groupBy { it % 5 })
}