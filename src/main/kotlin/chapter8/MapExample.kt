package chapter8

fun main() {
    val map = mapOf(
        "One" to 1,
        "Two" to 2,
        "Three" to 3,
        "Four" to 4,
        "Five" to 0,
        "Six" to 6,
        "Five" to 5
    )

    println("키 `Four`의 값은 ${map["Four"]} 이다")

    println("map의 내용")
    for (entry in map) {
        println("키 ${entry.key}, 값 ${entry.value}")
    }

    val mutableMap = mutableMapOf<Int, String>()

    mutableMap[1] = "아이템 1"
    mutableMap[2] = "아이템 2"
    mutableMap[3] = "아이템 3"
    mutableMap[4] = "아이템 4"

    println("키 1의 값 변경 - ${mutableMap.put(1, "아이템 5")}")

    println("mutableMap의 내용")
    for (entry in mutableMap) {
        println("키 ${entry.key}, 값 ${entry.value}")
    }
}