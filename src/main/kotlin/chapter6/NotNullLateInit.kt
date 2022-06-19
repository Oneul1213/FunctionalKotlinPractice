package chapter6

import kotlin.properties.Delegates

var notNullStr: String by Delegates.notNull<String>()
var notInit: String by Delegates.notNull<String>()

lateinit var notNullStr1: String
lateinit var notInit1: String

fun main() {
    notNullStr = "초기 값"
    println(notNullStr)
//    println(notInit)

    notNullStr1 = "초기 값"
    println(notNullStr1)
    println(notInit1)
}