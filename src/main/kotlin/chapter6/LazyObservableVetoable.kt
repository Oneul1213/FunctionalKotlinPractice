package chapter6

import kotlin.properties.Delegates

// 느긋한 함수
val myLazyVal: String by lazy {
    println("방금 초기화 됨")
    "느긋한 val"
}

// Delegates.observable
var myStr: String by Delegates.observable("<초기값>") {
    property, oldValue, newValue ->
        println("속성 `${property.name}`(을)를 '$oldValue'에서 '$newValue'로 변경한다")
}

// Delegates.vetoable
var myIntEven: Int by Delegates.vetoable(0) {
    property, oldValue, newValue ->
        println("${property.name} $oldValue -> $newValue")
    newValue % 2 == 0
}

var myCounter: Int by Delegates.vetoable(0) {
    property, oldValue, newValue ->
    println("${property.name} $oldValue -> $newValue")
    newValue > oldValue
}

fun main() {
    // 느긋한 함수
    println("아직 초기화되지 않음")
    println(myLazyVal)

    // Delegates.observable
    myStr = "값 변경"
    myStr = "값 재변경"

    // Delegates.vetoable
    myIntEven = 6
    myIntEven = 3
    println("myIntEven: $myIntEven")

    myCounter = 2
    println("myCounter: $myCounter")
    myCounter = 5
    myCounter = 4
    println("myCounter: $myCounter")
    myCounter++
    myCounter--
    println("myCounter: $myCounter")
}