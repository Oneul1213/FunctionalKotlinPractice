package chapter9

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.toObservable

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

    val list: List<String> = listOf("Str 1", "Str 2", "Str3", "Str4")

    val observable: Observable<String> = list.toObservable()

    observable.subscribe(observer)
}