package chapter7

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FutureUserService(private val userClient: UserClient,
        private val factClient: FactClient,
        private val userRepository: UserRepository,
        private val factRepository: FactRepository): UserService {
    override fun getFact(id: UserId): Fact {
        val executor = Executors.newFixedThreadPool(2)
        val user = executor.submit<User?> { userRepository.getUserById(id) }.get()

        return if (user == null) {
            val userFromService = executor.submit<User> {
                userClient.getUser(id)
            }.get()
            executor.submit { userRepository.insertUser(userFromService) }
            getFact(userFromService, executor)
        } else {
            executor.submit<Fact> {
                factRepository.getFactByUserId(id) ?: getFact(user, executor)
            }.get()
        }.also {
            executor.shutdown()
        }
    }

    private fun getFact(user: User, executor: ExecutorService): Fact {
        val fact = executor.submit<Fact> { factClient.getFact(user) }.get()
        executor.submit { factRepository.insertFact(fact) }
        return fact
    }
}