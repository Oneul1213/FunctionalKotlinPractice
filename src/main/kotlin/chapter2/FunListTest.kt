package chapter2

import chapter2.FunList.Cons
import chapter2.FunList.Nil

fun intListOf(vararg numbers: Int): FunList<Int> {
    return if (numbers.isEmpty()) {
        Nil
    } else {
        Cons(numbers.first(), intListOf(*numbers.drop(1).toTypedArray()
            .toIntArray()))
    }
}

fun main(args: Array<String>) {
    // 함수형 리스트
//    val numbers = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    val numbers = intListOf(1, 2, 3, 4)
    numbers.forEach { i -> println("i = $i") }

    val sum = numbers.fold(0) { acc, i -> acc + i }

    val funList = intListOf(1, 2, 3, 4)
    val list = listOf(1, 2, 3, 4)

    println("funList에서 fold 실행 : ${executionTime { funList.fold(0) { acc, i -> acc + i } }}")
    println("list에서 fold 실행 : ${executionTime { list.fold(0)  { acc, i, -> acc + i } }}")
}