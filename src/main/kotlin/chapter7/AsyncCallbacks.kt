package chapter7

import kotlin.concurrent.thread

class CallbackUserClient(private val client: UserClient) {
    fun getUser(id: Int, callback: (User) -> Unit) {
        thread {
            callback(client.getUser(id))
        }
    }
}

class CallbackFactClient(private val client: FactClient) {
    fun get(user: User, callback: (Fact) -> Unit) {
        thread {
            callback(client.getFact(user))
        }
    }
}

class CallbackUserRepository(private val userRepository: UserRepository) {
    fun getUserById(id: UserId, callback: (User?) -> Unit) {
        thread {
            callback(userRepository.getUserById(id))
        }
    }

    fun insertUser(user: User, callback: () -> Unit) {
        thread {
            userRepository.insertUser(user)
            callback()
        }
    }
}

class CallbackFactRepository(private val factRepository: FactRepository) {
    fun getFactByUserId(id: Int, callback: (Fact?) -> Unit) {
        thread {
            callback(factRepository.getFactByUserId(id))
        }
    }

    fun insertFact(fact: Fact, callback: () -> Unit) {
        thread {
            factRepository.insertFact(fact)
            callback()
        }
    }
}