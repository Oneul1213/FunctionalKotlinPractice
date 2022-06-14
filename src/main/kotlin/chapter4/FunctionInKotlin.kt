package chapter4

import java.util.regex.Pattern

// 코틀린의 함수
fun add(a: Int, b: Int): Int {
    val result = a+b
    return result
}

// 함수에서 두 개의 값 반환
fun getUser(): Pair<Int, String> {
    return Pair(1, "리부")
}

// 확장 함수
fun String.countWords(): Int {
    return trim()
        .split(Pattern.compile("\\s+"))
        .size
}

// 기본 인자
fun Int.isGreaterThan(anotherNumber: Int=0): Boolean {
    return this > anotherNumber
}

fun main(args: Array<String>) {
    // 함수에서 두 개의 값 반환
    val (userID, userName) = getUser()
    println("유저 ID: $userID t 유저명: $userName")

    // 확장 함수
    val counts = "이것은 여러 단어를 가진 예제 문자열이다".countWords()
    println("단어 개수: $counts")

    // 기본 인자
    println("5>0: ${5.isGreaterThan()}")
    println("5>6: ${5.isGreaterThan(6)}")

    // 중첩 함수
    fun nested(): String {
        return "중첩 함수에서의 문자열"
    }
    println("중첩 출력: ${nested()}")
}