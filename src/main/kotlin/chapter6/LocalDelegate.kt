package chapter6

fun useDelegate(shouldPrint: Boolean) {
    val localDelegate by lazy {
        "델리게이트 사용됨"
    }

    if (shouldPrint) {
        println(localDelegate)
    }

    println("바이 바이")
}