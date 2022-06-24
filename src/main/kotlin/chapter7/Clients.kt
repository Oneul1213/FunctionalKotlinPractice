package chapter7

interface UserClient {
    fun getUser(id: UserId): User
}

interface FactClient {
    fun getFact(user: User): Fact
}