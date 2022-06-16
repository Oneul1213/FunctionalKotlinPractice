package chapter5

infix fun Int.superOperation(i: Int) = this + i

infix fun String.`★`(s: String) = "$this show star $s"

object All {
    infix fun your(base: Pair<Base, Us>) {}
}

object Base {
    infix fun are(belong: Belong) = this
}

object Belong

object Us

fun main() {
    println(1 superOperation 2)
    println(1.superOperation(2))

    println("Kotlin" `★` "Ko")

    All your (Base are Belong to Us)
}