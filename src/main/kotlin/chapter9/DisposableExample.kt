package chapter9

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)
    val observer = object: Observer<Long> {

        lateinit var disposable: Disposable

        override fun onSubscribe(d: Disposable) {
            disposable = d
        }

        override fun onNext(item: Long) {
            println("받음 $item")
            if (item >= 10 && !disposable.isDisposed) {
                disposable.dispose()
                println("정리됨")
            }
        }

        override fun onError(e: Throwable) {
            println("에러 ${e.message}")
        }

        override fun onComplete() {
            println("완료")
        }
    }

    runBlocking {
        observable.subscribe(observer)
        delay(1500)
    }
}
