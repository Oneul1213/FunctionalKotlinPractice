package chapter3

class MutableObject {
    var mutableProperty: Int = 1
}

object MutableVal {
    var count = 0
    const val myString: String = "가변"
//    get() {
//        return "$field ${++count}"
//    }
}

fun main(args: Array<String>) {
//    var x: String = "abc"
//    var y = x.capitalize()
//    println("x = $x, y = $y")

//    val x: String = "코틀린"
//    x += "불변"

    println("1번째 호출 ${MutableVal.myString}")
    println("2번째 호출 ${MutableVal.myString}")
    println("3번재 호출 ${MutableVal.myString}")
}