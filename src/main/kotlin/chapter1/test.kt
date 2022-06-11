package chapter1

typealias Flavour = String abstract class BakeryGood(val flavour: String) {
    fun eat(): String {
        return "냠냠냠, 맛있는 $flavour ${name()}"
    }

    abstract fun name(): String
}

interface Bakeable {
    fun bake(): String {
        return "이곳은 뜨거워. 그렇지??"
    }
}

interface Machine<T> {
    fun process(product: T)
}

typealias Oven = Machine<Bakeable>

typealias OvenTray = List<Bakeable>

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Tasty(val tasty: Boolean = true)

@Tasty(false)
object ElectricOven: Oven {
    override fun process(product: Bakeable) {
        println(product.bake())
    }
}

open class Roll(flavour: String) : BakeryGood(flavour) {
    override fun name(): String {
        return "롤"
    }
}

@Tasty
class CinnamonRoll: Roll("시나몬")

@Tasty interface Fried {
    fun fry(): String
}

class Cupcake(flavour: String) : BakeryGood(flavour), Bakeable {
    override fun name(): String {
        return "컵케이크"
    }

    companion object {
        fun almond(): Cupcake {
            return Cupcake("아몬드")
        }

        fun cheese(): Cupcake {
            return Cupcake("치즈")
        }
    }
}

typealias CupcakeFactory = Cupcake.Companion

fun eat(cupcake: Cupcake?) {

}

data class Item(val product: BakeryGood,
    val unitPrice: Double,
    val quantity: Int)

interface Exotic {
    fun isExotic(): Boolean
}

enum class Flour: Exotic {
    WHEAT {
        override fun isExotic(): Boolean {
            return false
        }

        override fun isGlutenFree(): Boolean {
            return false
        }
    },
    CORN {
        override fun isExotic(): Boolean {
            return true
        }

        override fun isGlutenFree(): Boolean {
            return false
        }
    },
    CASSAVA {
        override fun isExotic(): Boolean {
            return true
        }

        override fun isGlutenFree(): Boolean {
            return true
        }
    };
    abstract fun isGlutenFree(): Boolean
}

fun flourDescription(flour: Flour): String {
    return when(flour) {
        Flour.CASSAVA -> "매우 이국적인 맛"
        else -> "실패함"
    }
}

fun main(args: Array<String>) {
    val annotations: List<Annotation> = ElectricOven::class.annotations
    for (annotation in annotations) {
        when (annotation) {
            is Tasty -> println("이것은 맛있는가? ${annotation.tasty}")
            else -> println(annotation)
        }
    }
}