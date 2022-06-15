package chapter4

fun performOperationOnEven(number: Int, operation: (Int)->Int):Int {
    if(number%2 == 0) {
        return operation(number)
    } else {
        return number
    }
}

fun getAnotherFunction(n: Int): (String)->Unit {
    return {
        println("n: $n it: $it")
    }
}

fun main() {
    println("4, (it*2)로 호출 : ${performOperationOnEven(4,
        {it * 2})}")
    println("5, (it*2)로 호출 : ${performOperationOnEven(5,
        {it * 2})}")

    getAnotherFunction(0)("abc")
    getAnotherFunction(2)("def")
    getAnotherFunction(3)("ghi")
}