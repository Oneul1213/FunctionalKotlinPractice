package chapter9

import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable

fun main() {
    var list = listOf(1, "둘", 3, "넷", "다섯", 5.5f)
    var observable = list.toObservable()

    observable.subscribeBy(
        onNext={ println(it) },
        onError={ it.printStackTrace() },
        onComplete={ println("완료!") }
    )
}