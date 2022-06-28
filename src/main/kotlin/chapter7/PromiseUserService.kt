package chapter7

import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.task
import nl.komponents.kovenant.then

class PromiseUserService(private val userClient: UserClient,
        private val factClient: FactClient,
        private val userRepository: UserRepository,
        private val factRepository: FactRepository): UserService {
    override fun getFact(id: UserId): Fact {
        return (task{
            userRepository.getUserById(id)
        } then { user ->
            if (user == null) {
                task {
                    userClient.getUser(id)
                } success { userFromService ->
                    userRepository.insertUser(userFromService)
                } then { userFromService ->
                    getFact(userFromService).get()
                }
            } else {
                task { factRepository.getFactByUserId(id)?: getFact(user).get() }
            }
        }).get().get()
    }

    private fun getFact(user: User): Promise<Fact, Exception> = task {
        factClient.getFact(user)
    } success { fact ->
        factRepository.insertFact(fact)
    }
}