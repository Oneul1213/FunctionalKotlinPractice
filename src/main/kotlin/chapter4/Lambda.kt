package chapter4

fun invokeSomeStuff(doSomeStuff: ()->Unit) {
    doSomeStuff()
}

fun main() {
    invokeSomeStuff {
        println("doSomeStuff 호출됨")
    }

    // 속성으로서의 함수
    val sum = { x: Int, y: Int -> x + y }
    println("Sum ${sum(10, 13)}")
    println("Sum ${sum(50, 68)}")

    val reverse: (Int)->Int = {
        var n = it
        var revNumber = 0
        while (n > 0) {
            val digit = n % 10
            revNumber = revNumber * 10 + digit
            n /= 10
        }
        revNumber
    }

    println("reverse 123 ${reverse(123)}")
    println("reverse 123 ${reverse(456)}")
    println("reverse 123 ${reverse(789)}")
}