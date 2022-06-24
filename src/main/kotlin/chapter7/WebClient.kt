package chapter7

import com.github.salomonbrys.kotson.*
import com.google.gson.GsonBuilder
import org.http4k.client.ApacheClient
import java.util.*

fun String.capitalize(): String =
     this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
        else it.toString()
    }

abstract class WebClient {
    protected val apacheClient = ApacheClient()
    protected val gson = GsonBuilder()
        .registerTypeAdapter<User> {
            deserialize { des ->
                val json = des.json
                User(json["info"]["seed"].int,
                    json["result"][0]["name"]["first"].string.capitalize(),
                    json["results"][0]["name"]["last"].string.capitalize(),
                    Gender.valueOfIgnoreCase(json["results"][0]["gender"].string))
            }
        }
        .registerTypeAdapter<Fact> {
            deserialize { des ->
                val json = des.json
                Fact(json["value"]["id"].int,
                json["value"]["joke"].string)
            }
        }.create()!!
}