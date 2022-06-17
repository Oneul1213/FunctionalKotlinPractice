package chapter5

class Wolf(val name: String) {
    operator fun plus(wolf: Wolf) = Pack(mapOf(name to this, wolf.name to wolf))

    operator fun invoke(action: WolfActions) = when (action) {
        WolfActions.SLEEP -> "$name(은)는 자는 중이다."
        WolfActions.WALK -> "$name(은)는 걷고 있다"
        WolfActions.BITE -> "$name(은)는 물어뜯는 중이다."
    }
}

class Pack(val members: Map<String, Wolf>)

// 바이너리 연산자
operator fun Pack.plus(wolf: Wolf) = Pack(this.members.toMutableMap() + (wolf.name to wolf))

// 인덱싱된 접근
operator fun Pack.get(name: String) = members[name]!!

// Invoke
enum class WolfActions {
    SLEEP, WALK, BITE
}

enum class WolfRelationships {
    FRIEND, SIBLING, ENEMY, PARTNER
}

operator fun Wolf.set(relationship: WolfRelationships, wolf: Wolf) {
    println("${wolf.name} (은)는 내 새로운 $relationship (이)다")
}

// Unary 연산자

operator fun Wolf.not() = "$name (은)는 화났다!!!"

fun main() {
    val talbot = Wolf("탈봇")
    val northPack: Pack = talbot + Wolf("빅 버사")

    // 바이너리 연산자
    val biggerPack = northPack + Wolf("나쁜 늑대")

    // Invoke
    talbot(WolfActions.SLEEP)

    // 인덱싱된 접근
    val badWolf = biggerPack["나쁜 늑대"]

    talbot[WolfRelationships.ENEMY] = badWolf

    println(!talbot)
}