package chapter5

fun <T> time(body: () -> T): Pair<T, Long> {
    val startTime = System.nanoTime()
    var v = body()
    val endTime = System.nanoTime()
    return v to endTime - startTime
}

inline fun <T> inTime(body: () -> T): Pair<T, Long> {
    val startTime = System.nanoTime()
    val v = body()
    val endTime = System.nanoTime()
    return v to endTime - startTime
}

// 인라인 제한
data class User(val name: String)
class UserService {
    val listeners = mutableListOf<(User) -> Unit>()
    val users = mutableListOf<User>()
    inline fun addListener(noinline listener: (User) -> Unit) {
        listeners += listener
    }

    inline fun transformName(crossinline transform: (name: String) -> String): List<User> {

        val buildUser = { name: String ->
            User(transform(name))
        }
        return users.map { user -> buildUser(user.name) }
    }

}

fun main() {
    val (_, time) = time { Thread.sleep(1000) }
    println("time = $time")

    val (_, inTime) = inTime { Thread.sleep(1000) }
    println("inTime = $inTime")

    // 인라인 제한
    val service = UserService()
    service.transformName(String::toLowerCase)
}