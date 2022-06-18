package chapter5

fun tailrecFib(n: Long): Long {
    tailrec fun go(n: Long, prev: Long, cur: Long): Long {
        return if (n == 0L) {
            prev
        } else {
            go(n-1, cur, prev + cur)
        }
    }

    return go(n, 0, 1)
}

fun tailrecFactorial(n: Long): Long {
    tailrec fun go(n: Long, acc: Long): Long {
        return if (n <= 0) {
            acc
        } else {
            go(n-1, n * acc)
        }
    }

    return go(n, 1)
}

sealed class FunList<out T> {
    object Nil: FunList<Nothing>()

    data class Cons<out T>(val head: T, val tail: FunList<T>): FunList<T>()

    fun forEach(f: (T) -> Unit) {
        tailrec fun go(list: FunList<T>, f: (T) -> Unit) {
            when(list) {
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
        tailrec fun go(list: FunList<T>, init: R, f:(R, T) -> R): R = when(list) {
            is Cons -> go(list.tail, f(init, list.head), f)
            is Nil -> init
        }

        return go(this, init, f)
    }

    fun reverse(): FunList<T> = fold(Nil as FunList<T>) { acc, i -> Cons(i, acc) }

    fun <R> foldRight(init: R, f: (R, T) -> R): R = this.reverse().fold(init, f)

    fun <R> mal(f: (T) -> R): FunList<R> = foldRight(Nil as FunList<R>) { tail, head ->
        Cons(f(head), tail)
    }
}

fun <T, S> unfold(s: S, f: (S) -> Pair<T, S>?): Sequence<T> {
    val result = f(s)
    return if (result != null) {
        sequenceOf(result.first) + unfold(result.second, f)
    } else {
        sequenceOf()
    }
}

fun <T> elements(element: T, numOfValues: Int): Sequence<T> {
    return unfold(1) { i ->
        if (numOfValues > i)
            element to i + 1
        else
            null
    }
}

fun factorial(size: Int): Sequence<Long> {
    return sequenceOf(1L) + unfold(1L to 1) { (acc, n) ->
        if (size > n) {
            val x = n * acc
            (x) to (x to n + 1)
        } else
            null
    }
}

fun fib(size: Int): Sequence<Long> {
    return sequenceOf(1L) + unfold(Triple(0L, 1L, 1)) { (cur, next, n) ->
        if (size > n) {
            val x = cur + next
            (x) to Triple(next, x, n + 1)
        }
        else
            null
    }
}

fun main() {
    val strings = elements("코틀린", 5)
    strings.forEach(::println)
}