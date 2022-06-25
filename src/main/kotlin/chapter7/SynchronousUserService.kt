package chapter7

class SynchronousUserService(private val userClient: UserClient,
        private val factClient: FactClient,
        private val userRepository: UserRepository,
        private val factRepository: FactRepository): UserService {
    override fun getFact(id: UserId): Fact {
        val user = userRepository.getUserById(id)
        return if (user == null) {
            val userFromService = userClient.getUser(id)
            userRepository.insertUser(userFromService)
            getFact(userFromService)
        } else {
            factRepository.getFactByUserId(id) ?: getFact(user)
        }
    }

    private fun getFact(user: User): Fact {
        val fact = factClient.getFact(user)
        factRepository.insertFact(fact)
        return fact
    }
}