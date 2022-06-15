package chapter4

// 부수 효과
class Calc {
    var a: Int = 0
    var b: Int = 0

    fun addNumbers(a: Int = this.a, b: Int = this.b):Int {
        this.a = a
        this.b = b
        return a+b
    }
}

// 순수 함수
fun addNumbers(a: Int = 0, b: Int = 0): Int {
    return a + b
}

fun main() {
    // 부수 효과
    val calc = Calc()
    println("결과는 ${calc.addNumbers(10, 15)} 이다")

    // 순수 함수
    println("결과는 ${addNumbers(10, 15)} 이다")
}