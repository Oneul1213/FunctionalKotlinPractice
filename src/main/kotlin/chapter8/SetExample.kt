package chapter8

data class MyDataClass(val someNumericValue: Int, val someStringValue: String)

class MyCustomClass(val someNumericValue: Int, val someStringValue: String) {
    override fun toString(): String {
        return "MyCustomClass(someNumericValue=$someNumericValue, someStringValue=$someStringValue)"
    }

    override fun hashCode() = someStringValue.hashCode() + someNumericValue.hashCode()

    override fun equals(other: Any?): Boolean {
        return other is MyCustomClass && other.someNumericValue == someNumericValue && other.someStringValue == someStringValue
    }
}

fun main() {
    val set = mutableSetOf(1, 2, 3, 3, 2)
    println("set $set")
    set.add(4)
    set.add(5)
    set.add(5)
    set.add(6)
    println("set $set")

    println("----------")

    val dataClassSet = setOf(
        MyDataClass(1, "1번째 오브젝트"),
        MyDataClass(2, "2번째 오브젝트"),
        MyDataClass(3, "3번째 오브젝트"),
        MyDataClass(2, "2번째 오브젝트"),
        MyDataClass(4, "4번째 오브젝트"),
        MyDataClass(5, "5번째 오브젝트"),
        MyDataClass(2, "추가될 것이다"),
        MyDataClass(3, "3번째 오브젝트")
    )
    println("dataClassSet의 아이템을 하나씩 출력한다")
    for (item in dataClassSet) {
        println(item)
    }

    val customClassSet = setOf(
        MyCustomClass(1, "1번째 오브젝트"),
        MyCustomClass(2, "2번째 오브젝트"),
        MyCustomClass(3, "3번째 오브젝트"),
        MyCustomClass(2, "2번째 오브젝트"),
        MyCustomClass(4, "4번째 오브젝트"),
        MyCustomClass(5, "5번째 오브젝트"),
        MyCustomClass(5, "5번째 오브젝트"),
        MyCustomClass(3, "3번째 오브젝트"),
    )
    println("customClassSet의 아이템을 하나씩 출력한다")
    for (item in customClassSet) {
        println(item)
    }
}