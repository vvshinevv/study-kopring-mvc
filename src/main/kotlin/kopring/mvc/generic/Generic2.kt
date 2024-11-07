package kopring.mvc.generic

fun main() {
    val toy = Toy()
    val toyBox: Box<Toy> = Box(toy)
}

data class Box<T>(var content: T) {

}

class BoxHandler {
    fun getToy(box: Box<out Toy>) {
        box.content
    }

    fun putToy(box: Box<in Toy>) {
        val toy = Toy()
        box.content = toy
    }
}

class ListHandler {
    fun read(list: MutableList<out Toy>) {
        list[0]
    }

    fun readAll(list: MutableList<out Toy>) {
        for (toy: Toy in list) {
            println(toy)
        }
    }

    fun write(list: MutableList<in Toy>) {
        list.add(Toy())
    }

}

open class Plastic

open class Toy : Plastic()

open class Car : Toy()

open class Robot : Toy()
