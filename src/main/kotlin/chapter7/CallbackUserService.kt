package chapter7

class CallbackUserService(private val userClient: CallbackUserClient,
        private val factClient: CallbackFactClient,
        private val userRepository: CallbackUserRepository,
        private val factRepository: CallbackFactRepository): UserService {
    override fun getFact(id: UserId): Fact {
        var aux: Fact? = null
        userRepository.getUserById(id) { user ->
            if (user == null) {
                userClient.getUser(id) { userFromClient ->
                    userRepository.insertUser(userFromClient) {}
                    factClient.get(userFromClient) { fact ->
                        factRepository.insertFact(fact) {}
                        aux = fact
                    }
                }
            } else {
                factRepository.getFactByUserId(id) { fact ->
                    if (fact == null) {
                        factClient.get(user) { factFromClient ->
                            factRepository.insertFact(factFromClient) {}
                            aux = factFromClient
                        }
                    } else {
                        aux = fact
                    }
                }
            }
        }

        while (aux == null) {
            Thread.sleep(2)
        }
        return aux!!
    }
}