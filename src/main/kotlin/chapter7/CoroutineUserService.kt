package chapter7

import kotlinx.coroutines.*

class CoroutineUserService(private val userClient: UserClient,
        private val factClient: FactClient,
        private val userRepository: UserRepository,
        private val factRepository: FactRepository): UserService {
    override fun getFact(id: UserId): Fact = runBlocking {
        val user = async { userRepository.getUserById(id) }.await()
        if (user == null) {
            val userFromService = async { userClient.getUser(id) }.await()
            launch { userRepository.insertUser(userFromService) }
            getFact(userFromService)
        } else {
            async { factRepository.getFactByUserId(id) ?: getFact(user) }.await()
        }
    }

    private suspend fun getFact(user: User): Fact {

        val fact: Deferred<Fact> = GlobalScope.async { factClient.getFact(user) }
        GlobalScope.launch { factRepository.insertFact(fact.await()) }
        return fact.await()
    }
}