package chapter7

import com.github.salomonbrys.kotson.fromJson
import org.http4k.core.Method
import org.http4k.core.Request

class Http4kUserClient: WebClient(), UserClient {
    override fun getUser(id: UserId): User {
        return gson.fromJson(apacheClient(Request(Method.GET,
            "https://randomuser.me/api")
                .query("seed", id.toString()))
                .bodyString())
    }
}

class Http4kFactClient: WebClient(), FactClient {
    override fun getFact(user: User): Fact {
        return gson.fromJson<Fact>(apacheClient(Request(Method.GET,
            "http://api.icndb.com/jokes/random")
                .query("firstName", user.firstName)
                .query("lastName", user.lastName))
                .bodyString())
                .copy(user=user)
    }
}