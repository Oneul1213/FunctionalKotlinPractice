package chapter7

class MockUserRepository: UserRepository {
    private val users = hashMapOf<UserId, User>()

    override fun getUserById(id: UserId): User? {
        println("MockUserRepository.getUserById")
        Thread.sleep(200)
        return users[id]
    }

    override fun insertUser(user: User) {
        println("MockUserRepository.insertUser")
        Thread.sleep(200)
        users[user.id] = user
    }
}

class MockFactRepository: FactRepository {
    private val facts = hashMapOf<UserId, Fact>()

    override fun getFactByUserId(id: UserId): Fact? {
        println("MockFactRepository.getFactByUserId")
        Thread.sleep(200)
        return facts[id]
    }

    override fun insertFact(fact: Fact) {
        println("MockFactRepository.insertFact")
        Thread.sleep(200)
        facts[fact.user?.id ?: 0] = fact
    }
}