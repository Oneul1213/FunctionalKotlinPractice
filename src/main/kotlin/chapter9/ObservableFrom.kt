package chapter9

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.operators.observable.ObservableFromCallable
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

fun main() {
    val observer: Observer<String> = object: Observer<String> {
        override fun onComplete() {
            println("완료됨")
        }

        override fun onNext(item: String) {
            println("받음 -> $item")
        }

        override fun onError(e: Throwable) {
            println("에러 발생 => ${e.message}")
        }

        override fun onSubscribe(d: Disposable) {
            println("구독")
        }
    }

    val list = listOf("Str 1", "Str 2", "Str 3", "Str 4")
    val observableFromIterable: Observable<String> = Observable.fromIterable(list)

    observableFromIterable.subscribe(observer)

    val callable = object: Callable<String> {
        override fun call(): String {
            return "Callable에서 왔다"
        }
    }

    val observableFromCallable: Observable<String> = ObservableFromCallable(callable)

    observableFromCallable.subscribe(observer)

    val future: Future<String> = object: Future<String> {
        val retStr = "Future에서 왔다"

        override fun get() = retStr

        override fun get(timeout: Long, unit: TimeUnit) = retStr

        override fun isDone(): Boolean = true

        override fun isCancelled(): Boolean = false

        override fun cancel(mayInterruptIfRunning: Boolean): Boolean = false
    }

    val observableFromFuture: Observable<String> = Observable.fromFuture(future)

    observableFromFuture.subscribe(observer)
}