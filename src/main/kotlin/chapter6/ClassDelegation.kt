package chapter6

interface Person {
    fun printName()
}

class PersonImpl(val name: String): Person {
    override fun printName() {
        println(name)
    }
}

class User(val person: Person): Person by person {
    override fun printName() {
        println("이름 출력: ")
        person.printName()
    }
}

fun main() {
    val person = PersonImpl("마리오 아리아스")
    person.printName()
    println()
    val user = User(person)
    user.printName()
}