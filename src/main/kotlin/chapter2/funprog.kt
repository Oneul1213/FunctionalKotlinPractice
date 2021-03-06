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

// 재귀함수
fun factorial(n: Long): Long {
    var result = 1L
    for (i in 1..n) {
        result *= i
    }

    return result
}

fun functionalFactorial(n: Long): Long {
    fun go(n: Long, acc: Long): Long {
        return if (n <= 0) {
            acc
        } else {
            go(n - 1, n * acc)
        }
    }
    return go(n, 1)
}

fun tailrecFactorial(n: Long): Long {
    tailrec fun go(n: Long, acc: Long): Long {
        return if (n <= 0) {
            acc
        } else {
            go(n - 1, n * acc)
        }
    }
    return go(n, 1)
}

fun executionTime(body: () -> Unit): Long {
    val startTime = System.nanoTime()
    body()
    val endTime = System.nanoTime()
    return endTime - startTime
}

fun fib(n: Long): Long {
    return when(n) {
        0L -> 0
        1L -> 1
        else -> {
            var a = 0L
            var b = 1L
            var c = 0L
            for (i in 2..n) {
                c = a + b
                a = b
                b = c
            }
            c
        }
    }
}

fun functionalFib(n: Long): Long {
    fun go(n: Long, prev: Long, cur: Long): Long {
        return if (n == 0L) {
            prev
        } else {
            go(n - 1, cur, prev + cur)
        }
    }

    return go(n, 0, 1)
}

fun tailrecFib(n: Long): Long {
    tailrec fun go(n: Long, prev: Long, cur: Long): Long {
        return if (n == 0L) {
            prev
        } else {
            go(n - 1, cur, prev + cur)
        }
    }

    return go(n, 0, 1)
}

// 함수적 컬렉션
val numbers: List<Int> = listOf(1, 2, 3, 4)

// 함수형 리스트
sealed class FunList<out T> {
    object Nil: FunList<Nothing>()

    data class Cons<out T> (val head: T, val tail: FunList<T>): FunList<T>()

    fun forEach(f: (T) -> Unit) {
        tailrec fun go(list: FunList<T>, f: (T) -> Unit) {
            when (list) {
                is Cons -> {
                    f(list.head)
                    go(list.tail, f)
                }
                is Nil -> Unit
            }
        }
        go(this, f)
    }

    fun <R> fold(init: R, f: (R, T) -> R): R {
        tailrec fun go(list: FunList<T>, init: R, f: (R, T) -> R): R = when
                (list) {
                    is Cons -> go(list.tail, f(init, list.head), f)
                    is Nil -> init
                }
        return go(this, init, f)
    }

    fun reverse(): FunList<T> = fold(Nil as FunList<T>) { acc, i -> Cons(i, acc) }

    fun <R> foldRight(init: R, f: (R, T) -> R): R {
        return this.reverse().fold(init, f)
    }

    fun <R> map(f: (T) -> R): FunList<R> {
        return foldRight(Nil as FunList<R>) { tail, head -> Cons(f(head), tail) }
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

    println("factorial : " + executionTime { factorial(20) })
    println("functionalFactorial : " + executionTime { functionalFactorial(20) })
    println("tailrecFactorial : " + executionTime { tailrecFactorial(20) })

    println("fib : " + executionTime { fib(93) })
    println("functionalFib : " + executionTime { functionalFib(93) })
    println("tailrecFib : " + executionTime { tailrecFib(93) })

    // lazy
    val i by lazy {
        println("느긋한 계산법")
        1
    }

    println("i 사용 전")
    println(i)

    val size = listOf({2+1}, {3*2}, {1/0}, {5-4}).size

    // 함수적 컬렉션
    for(i in numbers) {
        println("i = $i")
    }

    numbers.forEach { i -> println("i = $i") }

//    val numbersTwice: MutableList<Int> = mutableListOf()
//
//    for(i in numbers) {
//        numbersTwice.add(i * 2)
//    }

    val numbersTwice: List<Int> = numbers.map { i -> i * 2 }

//    var sum = 0
//
//    for (i in numbers) {
//        sum += i
//    }
//    println(sum)
//
//    val sum = numbers.sum()
//    println(sum)
//
//    val sum = numbers.fold(0) { acc, i -> acc + i }
//    println(sum)
//
//    val sum = numbers.fold(0) { acc, i ->
//        println("acc, i = $acc, $i")
//        acc + i
//    }
//    println(sum)

    val sum = numbers.reduce { acc, i ->
        println("acc, i = $acc, $i")
        acc + i
    }
    println(sum)
}