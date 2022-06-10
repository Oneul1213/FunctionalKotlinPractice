package chapter3

class MutableObject {
    var mutableProperty: Int = 1
}

object MutableVal {
    var count = 0
    const val myString: String = "가변"
//    get() {
//        return "$field ${++count}"
//    }
}

// 참조 불변
class MutableObj {
    var value =""
    override fun toString(): String {
        return "MutableObj(value='$value')"
    }
}

fun main(args: Array<String>) {
//    var x: String = "abc"
//    var y = x.capitalize()
//    println("x = $x, y = $y")

//    val x: String = "코틀린"
//    x += "불변"

    println("1번째 호출 ${MutableVal.myString}")
    println("2번째 호출 ${MutableVal.myString}")
    println("3번재 호출 ${MutableVal.myString}")

    // 참조 불변
    val mutableObj: MutableObj = MutableObj()
    println("MutableObj $mutableObj")
    mutableObj.value = "changed"
    println("MutableObj $mutableObj")

    val list = mutableListOf("a", "b", "c", "d", "e")
    println(list)
    list.add("f")
    println(list)

    // 불변 컬렉션
    val immutableList = listOf(1, 2, 3, 4, 5, 6, 7)
    println("불변 리스트 $immutableList")
    val mutableList: MutableList<Int> = immutableList.toMutableList()
    println("가변 리스트 $mutableList")
    mutableList.add(8)
    println("추가 후의 가변 리스트 $mutableList")
    println("추가 후의 불변 리스트 $immutableList")
}