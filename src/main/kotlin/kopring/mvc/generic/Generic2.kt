package kopring.mvc.generic

fun main() {
    val toy = Toy()
    val toyBox: Box<Toy> = Box(toy)
}

data class Box<T>(var content: T) {
    fun setContent(content: T) {
        this.content = content
    }

    fun getContent(): T {
        return this.content
    }
}

class BoxHandler {
    fun getToy(box: Box<out Toy>) {
        val a = box.getContent()
        box.setContent(a)
    }

    fun putToy(box: Box<in Toy>) {
        val a: Toy = box.getContent()
        box.setContent(a)
    }
}

class ListHandler {
    fun read(list: MutableList<out Toy>) {
        val a: Toy = list[0]
        list.add(Toy())
    }

    fun readAll(list: MutableList<out Toy>) {
        for (toy: Toy in list) {
            println(toy)
        }
    }

    fun write(list: MutableList<in Toy>) {
        val a: Toy = list[0]
        list.add(Toy())
    }

    fun writeAll(list: MutableList<in Toy>) {
        for (toy: Toy in list) {
            list.add(toy)
        }
    }
}

open class Plastic

open class Toy : Plastic()

open class Car : Toy()

open class Robot : Toy()
