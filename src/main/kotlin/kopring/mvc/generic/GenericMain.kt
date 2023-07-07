package kopring.mvc.generic

fun main() {
    val intList = listOf(1, 2, 3)
    wildCardFun(intList)
}

fun wildCardFun(list: ArrayList<*>) {
    list[0]
}

fun <T> genericFun(list: ArrayList<T>) {
    list.add(list[0])
}

fun peekBox1(box: Box<Any>) {
    println(box)
}

fun <T> peekBox2(box: Box<T>) {
    println(box)
}

class Box<T>
