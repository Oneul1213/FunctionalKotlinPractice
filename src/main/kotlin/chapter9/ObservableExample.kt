package chapter9

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.toObservable

fun main() {
    val observer = object: Observer<Any> {
        override fun onComplete() {
            println("모두 완료됨")
        }

        override fun onNext(item: Any) {
            println("다음 $item")
        }

        override fun onError(e: Throwable) {
            println("에러 발생 $e")
        }

        override fun onSubscribe(d: Disposable) {
            println("$d 구독됨")
        }
    }

    val observable = listOf(1, "둘", 3, "넷", "다섯", 5.5f).toObservable()

    observable.subscribe(observer)

    val observableOnList = Observable.just(listOf(1, "둘", 3, "넷", "다섯", 6.0f),
        listOf("아이템 1개인 리스트"),
        listOf(1, 2, 3))

    observableOnList.subscribe(observer)
}