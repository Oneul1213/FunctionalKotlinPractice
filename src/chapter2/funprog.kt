package chapter2

val capitalize = { str: String -> str.capitalize() }

fun <T> transform(t: T, fn: (T) -> T): T {
    return fn(t)
}

fun reverse(str: String): String {
    return str.reversed()
}

object MyUtils {
    fun doNothing(str: String): String {
        return str
    }
}

class Transformer {
    fun upperCased(str: String): String {
        return str.uppercase()
    }

    companion object {
        fun lowerCased(str:String): String {
            return str.lowercase()
        }
    }
}

fun unless(condition: Boolean, block: () -> Unit) {
    if (!condition) block()
}

typealias Machine<T> = (T) -> Unit

fun <T> useMachine(t: T, machine: Machine<T>) {
    machine(t)
}

class PrintMachine<T>: Machine<T> {
    override fun invoke(p1: T) {
        println(p1)
    }
}

fun main(args: Array<String>) {
    println(capitalize("hello world!"))
    println(transform("kotlin", capitalize))
    println(transform("kotlin", ::reverse))
    println(transform("kotlin", MyUtils::doNothing))

    val transformer = Transformer()

    println(transform("kotlin", transformer::upperCased))
    println(transform("KOTLIN", Transformer.Companion::lowerCased))

    println(transform("kotlin", { str -> str.substring(0..1) }))
    println(transform("kotlin", { it.substring(0..1) }))

    println(transform("kotlin") { str -> str.substring(0..1) })

    val securityCheck = false
    unless(securityCheck) {
        println("이 웹 사이트에 접근할 수 없다")
    }

    useMachine(5, PrintMachine())

    useMachine(5, ::println)
    useMachine(5) { i ->
        println(i)
    }
}