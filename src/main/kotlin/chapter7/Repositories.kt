package chapter7

interface UserRepository {
    fun getUser(id: UserId): User?
    fun insertUser(user: User)
}

interface FactRepository {
    fun getFactByUser(id: UserId): Fact?
    fun insertFact(fact: Fact)
}