package chapter9

fun main() {
    var list: List<Any> = listOf(1, "둘", 3, "넷", "다섯", 5.5f)
    var iterator = list.iterator()

    while (iterator.hasNext()) {
        println(iterator.next())
    }
}