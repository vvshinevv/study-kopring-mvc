package kopring.mvc.generic

fun main() {
    val intArrayList: ArrayList<Int> = arrayListOf(1, 2, 3)
    wildCardFun(intArrayList)
    genericFun(intArrayList)


    val intContainer = Container(1)
    val doubleContainer = Container(1.1)

    func(intContainer)
}

fun wildCardFun(arrayList: ArrayList<*>) {
    arrayList.add(arrayList[0]) // compile error
}

fun <T> genericFun(arrayList: ArrayList<T>) {
    arrayList.add(arrayList[0])
}

fun func(container: Container<Number>) {

}

data class Container<T>(val data: T)
