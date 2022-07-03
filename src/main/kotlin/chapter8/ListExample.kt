package chapter8

fun main() {
    val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (i in list) {
        println("아이템 $i")
    }

    println("----------")

    val emptyList1 = listOf<Any>()
    val emptyList2 = emptyList<Any>()
    println("emptyList1.size = ${emptyList1.size}")
    println("emptyList2.size = ${emptyList2.size}")

    println("----------")

    val mutableList = mutableListOf(1, 2, 4)

    for (i in mutableList) {
        println("for1 아이템 $i")
    }
    println("-----아이템 추가-----")

    mutableList.add(5)
    mutableList.add(2, 3)
    mutableList.add(6)

    for (i in mutableList) {
        println("for2 아이템 $i")
    }

    println("----------")

    val list2 = listOf(
        "첫 번째 아이템",
        "두 번째 아이템",
        "세 번째 아이템",
        "네 번째 아이템",
        "다섯 번째 아이템"
    )

    println("리스트의 3번째 아이템 - ${list2.get(2)}")
    println("리스트의 4번째 아이템 - ${list2[3]}")
}