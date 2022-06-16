package chapter5

// 단일 표현 함수
fun sum(a: Int, b: Int) = a + b

// 파라미터
fun basicFunction(name: String, size: Int) {
}

// 파라미터 - 수정자 vararg
fun aVarargFun(vararg names: String) {
    names.forEach(::println)
}

//fun multipleVarargs(vararg names: String, vararg sizes: Int) {
//
//}

// 람다
fun unless(condition: Boolean, block: () -> Unit) {
    if (!condition) block()
}

fun <T, R> transform(vararg ts: T, f: (T) -> R): List<R> = ts.map(f)

fun <T> emit(t: T,vararg listeners: (T) -> Unit) = listeners.forEach {
    listener ->
        listener(t)
}

// 명명된 파라미터
typealias Kg = Double
typealias cm = Int

data class Customer(val firstName: String,
            val middleName: String,
            val lastName: String,
            val passportNumber: String,
            val weight: Kg,
            val height: cm)

fun paramAfterVararg(courseId: Int, vararg students: String, roomTemperature: Double) {

}

// 고차 함수의 명명된 파라미터
fun high(f: (age: Int, name: String) -> Unit) {
    f(1, "로미오")
}

// 기본 파라미터
data class Programmer(val firstName: String,
    val lastName: String,
    val favouriteLanguage: String = "코틀린",
    val yearsOfExperience: Int = 0)

fun main() {
    // 파라미터 - 수정자 vararg
    aVarargFun()
    aVarargFun("안젤라", "브렌다", "캐롤라인")

    // 람다
    val someBoolean = false
    unless(someBoolean) {
        println("이 웹사이트에 접근할 수 없다")
    }

    println(transform(1, 2, 3, 4) { i -> (i*2).toString() })

//    emit(1){ i -> println(i) }
    emit(1, ::println, { i -> println(i * 2) })

    // 명명된 파라미터
    val customer1 = Customer("존", "칼", "도", "XX234", 82.3, 180)

    val customer2 = Customer(
        lastName = "도",
        firstName = "존",
        middleName = "칼",
        height = 180,
        weight = 82.3,
        passportNumber = "XX234"
    )

    paramAfterVararg(68, "아벨", "바바라", "칼", "다이앤", roomTemperature = 18.0)

    // 고차 함수에 대한 명명된 파라미터
    high { q, w ->

    }

    // 기본 파라미터
    val programmer1 = Programmer("존", "도")

    val programmer2 = Programmer("존", "도", "TypeScript", 1)
    val programmer3 = Programmer("존", "도", yearsOfExperience = 12)
}