package chapter4

fun invokeSomeStuff(doSomeStuff: ()->Unit) {
    doSomeStuff()
}

fun main() {
    invokeSomeStuff {
        println("doSomeStuff 호출됨")
    }
}