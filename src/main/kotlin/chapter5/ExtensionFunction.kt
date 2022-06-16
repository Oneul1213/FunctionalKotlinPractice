package chapter5

fun String.sendToConsole() = println(this)
// fun sendToConsole(string: String) = println(string)

class Human(private val name: String)

//fun Human.speak(): String = "${this.name}(이)가 소리를 낸다"

// 확장 함수와 상속
open class Canine {
    open fun speak() = "<일반적인 개과 소리>"
}

class Dog: Canine() {
    override fun speak() = "멍멍!!"
}

fun printSpeak(canine: Canine) {
    println(canine.speak())
}

open class Feline

fun Feline.speak() = "<일반적인 고양잇과 소리>"

class Cat: Feline()

fun Cat.speak() = "야옹!!"

fun printSpeak(feline: Feline) {
    println(feline.speak())
}

open class Primate(val name: String)

fun Primate.speak() = "$name: <일반적인 영장류 소리>"

open class GiantApe(name: String): Primate(name)

fun GiantApe.speak() = "${this.name}: <무서운 100db 포효>"

fun printSpeak(primate: Primate) {
    println(primate.speak())
}

// 멤버로서의 확장 함수
open class Caregiver(val name: String) {
    open fun Feline.react() = "크엉!!!"

    fun Primate.react() = "*$name (은)는 ${this@Caregiver.name}(와)과 같이 논다*"

    fun takeCare(feline: Feline) {
        println("고양잇과 반응: ${feline.react()}")
    }
    fun takeCare(primate: Primate) {
        println("영장류 반응: ${primate.react()}")
    }
}

open class Vet(name: String): Caregiver(name) {
    override fun Feline.react() = "*$name (으)로부터 도망친다*"
}

// 충돌하는 이름을 가진 확장 함수
class Worker {
    fun work() = "*열심히 일한다*"
    private fun rest() = "*쉰다*"
}

fun Worker.work() = "*열심히 일하지는 않는다*"

fun <T> Worker.work(t:T) = "*$t 작업중*"

fun Worker.rest() = "*비디오 게임을 하는 중*"

// 오브젝트용 확장 함수
object Builder {
}

fun Builder.buildBridge() = "반짝거리는 새 다리"

class Designer {
    companion object {
    }

    object Desk {
    }
}

fun Designer.Companion.fastPrototype() = "프로토타입"
fun Designer.Desk.portfolio() = listOf("프로젝트1", "프로젝트2")

fun main() {
    "헬로 월드! (외부 함수로 부터)".sendToConsole()
    // sendToConsole("헬로 월드! (일반 함수로 부터)

    // 확장 함수와 상속
    printSpeak(Canine())
    printSpeak(Dog())

    printSpeak(Feline())
    printSpeak(Cat())

    printSpeak(Primate("코코"))
    printSpeak(GiantApe("콩"))

    // 멤버로서의 확장 함수
    val adam = Caregiver("아담")
    val fulgencio = Cat()
    val koko = Primate("코코")
    adam.takeCare(fulgencio)
    adam.takeCare(koko)

    val brenda = Vet("브렌다")
    listOf(adam, brenda).forEach { caregiver ->
        println("${caregiver.javaClass.simpleName} ${caregiver.name}")
        caregiver.takeCare(fulgencio)
        caregiver.takeCare(koko)
    }

    // 충돌하는 이름을 가진 확장 함수
    val worker = Worker()

    println(worker.work())

    println(worker.work("리팩토링"))

    println(worker.rest())

    // 오브젝트용 확장 함수
    println(Designer.fastPrototype())
    Designer.Desk.portfolio().forEach(::println)
}