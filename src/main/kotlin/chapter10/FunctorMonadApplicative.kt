package chapter10

sealed class Option<out T> {
    object None: Option<Nothing>() {
        override fun toString() = "None"
    }

    data class Some<out T>(val value: T): Option<T>()

    companion object
}

// Functor

//fun <T, R> Option<T>.map(transform: (T) -> R): Option<R> = when (this) {
//    Option.None -> Option.None
//    is Option.Some -> Option.Some(transform(value))
//}

fun <A, B, C> ((A) -> B).map(transform: (B) -> C): (A) -> C = { t ->
    transform(this(t))
}

// Monad
fun <T, R> Option<T>.flatMap(fm: (T) -> Option<R>): Option<R> = when(this) {
    Option.None -> Option.None
    is Option.Some -> fm(value)
}

fun <T, R> Option<T>.map(transform: (T) -> R): Option<R> = flatMap { t ->
    Option.Some(transform(t))
}

fun calculateDiscount(price: Option<Double>): Option<Double> {
    return price.flatMap { p ->
        if (p > 50.0) {
            Option.Some(5.0)
        } else {
            Option.None
        }
    }
}

// Applicative
fun <T, R> List<T>.ap(fab: List<(T) -> R>): List<R> = fab.flatMap { f -> this.map(f) }

fun <T> Option.Companion.pure(t: T): Option<T> = Option.Some(t)

fun <T, R> Option<T>.ap(fab: Option<(T) -> R>): Option<R> = fab.flatMap { f -> map(f) }

// Windows에서 오류 발생할 가능성이 있음.
// infix fun <T, R> Option<(T) -> R>.`(*)`(o: Option<T>): Option<R> = flatMap { f: (T) -> R -> o.map(f) }

// additional
object Function1 {
    fun <A, B> pure(b: B) = { _: A -> b }
}

fun <A, B, C> ((A) -> B).flatMap(fm: (B) -> (A) -> C): (A) -> C = { t -> fm(this(t))(t) }

fun <A, B, C> ((A) -> B).ap(fab: (A) -> (B) -> C): (A) -> C = fab.flatMap { f -> map(f) }

fun main() {
    // Functor
    println("--Functor--")

    listOf(1, 2, 3)
        .map { i -> i * 2 }
        .map(Int::toString)
        .forEach(::println)

    println(Option.Some("Kotlin").map(String::toUpperCase))
    println(Option.None.map(String::toUpperCase))

    val add3AndMultiplyBy2: (Int) -> Int = { i: Int -> i + 3 }.map { j -> j * 2 }
    println(add3AndMultiplyBy2(0))
    println(add3AndMultiplyBy2(1))
    println(add3AndMultiplyBy2(2))

    // Monad
    println("--Monad--")

    val result = listOf(1, 2, 3)
        .flatMap { i -> listOf(i*2, i+3) }
        .joinToString()

    println(result)

    println(calculateDiscount(Option.Some(80.0)))
    println(calculateDiscount(Option.Some(30.0)))
    println(calculateDiscount(Option.None))

    val maybeFive = Option.Some(5)
    val maybeTwo = Option.Some(2)
    println(maybeFive.flatMap { f ->
        maybeTwo.flatMap { t ->
            Option.Some(f + t)
        }
    })

    println(maybeFive.flatMap { f ->
        maybeTwo.map { t ->
            f + t
        }
    })

    val numbers = listOf(1, 2, 3)
    val functions = listOf<(Int) -> Int>({ i -> i*2 }, { i -> i+3 })
    val result2 = numbers.flatMap { number ->
        functions.map { f -> f(number) }
    }.joinToString()
    println(result2)

    // Applicative
    println("--Applicative--")

    val numbers2 = listOf(1, 2, 3)
    val functions2 = listOf<(Int) -> Int>({ i -> i*2 }, { i -> i + 3 })
    val result3 = numbers2
        .ap(functions2)
        .joinToString()
    println(result3)

    println(maybeTwo.ap(maybeFive.map { f -> { t: Int -> f + t } }))

//    println(Option.pure { f: Int -> { t: Int -> f + t } } `(*)` maybeFive `(*)` maybeTwo)

    // additional
    val f: (String) -> Int = Function1.pure(0)
    println(f("Hello,"))
    println(f("World"))
    println(f("!"))

    val add3AndMultiplyBy2UseAp: (Int) -> Int = { i: Int -> i + 3 }.ap { { j: Int -> j * 2 } }
    println(add3AndMultiplyBy2UseAp(0))
    println(add3AndMultiplyBy2UseAp(1))
    println(add3AndMultiplyBy2UseAp(2))

    val add3AndMultiplyBy2UseApUpgrade: (Int) -> Pair<Int, Int>
            = { i: Int -> i + 3 }.ap { original -> { j: Int -> original to (j * 2) } }
    println(add3AndMultiplyBy2UseApUpgrade(0))
    println(add3AndMultiplyBy2UseApUpgrade(1))
    println(add3AndMultiplyBy2UseApUpgrade(2))
}