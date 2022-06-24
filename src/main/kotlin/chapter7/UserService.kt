package chapter7

import java.util.*

enum class Gender {
    MALE, FEMALE;

    companion object {
        fun valueOfIgnoreCase(name: String): Gender = valueOf(name.uppercase(Locale.getDefault()))
    }
}

typealias UserId = Int

data class User(val id: UserId, val firstName: String, val lastName: String, val gender: Gender)

data class Fact(val id: Int, val value: String, val user: User? = null)

interface UserService {
    fun getFact(id: UserId): Fact
}