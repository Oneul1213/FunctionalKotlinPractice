package chapter7

import java.util.*

class MockUserClient: UserClient {
    override fun getUser(id: UserId): User {
        println("MockUserClient.getUser")
        Thread.sleep(500)
        return User(id, "Foo", "Bar", Gender.FEMALE)
    }
}

class MockFactClient: FactClient {
    override fun getFact(user: User): Fact {
        println("MockFactClient.getFact")
        Thread.sleep(500)
        return Fact(Random().nextInt(), "FACT ${user.firstName}, ${user.lastName}", user)
    }
}