package chapter7

import chapter5.inTime

fun main() {
    fun execute(userService: UserService, id: Int) {
        val (fact, time) = inTime {
            userService.getFact(id)
        }
        println("진실 = $fact")
        println("시간 = $time ms")
    }

    val userClient = MockUserClient()
    val factClient = MockFactClient()
    val userRepository = MockUserRepository()
    val factRepository = MockFactRepository()
    val userService = CoroutineUserService(userClient,
        factClient,
        userRepository,
        factRepository)

    execute(userService, 1)
    execute(userService, 2)
    execute(userService, 1)
    execute(userService, 2)
    execute(userService, 3)
    execute(userService, 4)
    execute(userService, 5)
    execute(userService, 10)
    execute(userService, 100)
}