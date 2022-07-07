package chapter9

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

fun main() {
    val observable = Observable.range(1, 5)

    observable.subscribe({
        // onNext
        println("다음-> $it")
    }, {
        // onError
        println("에러=> ${it.message}")
    }, {
        // onComplete
        println("완료")
    })

    val observer: Observer<Int> = object: Observer<Int> {
        override fun onComplete() {
            println("모두 완료됨")
        }

        override fun onNext(item: Int) {
            println("다음 -> $item")
        }

        override fun onError(e: Throwable) {
            println("에러 발생 => ${e.message}")
        }

        override fun onSubscribe(d: Disposable) {
            println("새로운 구독")
        }
    }

    observable.subscribe(observer)
}