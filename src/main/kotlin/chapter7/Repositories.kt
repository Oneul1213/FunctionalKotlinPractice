package chapter7

interface UserRepository {
    fun getUserById(id: UserId): User?
    fun insertUser(user: User)
}

interface FactRepository {
    fun getFactByUserId(id: UserId): Fact?
    fun insertFact(fact: Fact)
}