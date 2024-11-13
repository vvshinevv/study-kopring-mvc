package kopring.mvc.funtional.chapter04

class PartialFunction<in P, out R>(private val condition: (P) -> Boolean, private val f: (P) -> R) : (P) -> R {
    override fun invoke(p: P): R = when {
        condition(p) -> f(p)
        else -> throw IllegalArgumentException("$p is not allowed")
    }

    fun isDefinedAt(p: P): Boolean = condition(p)
}

fun <P, R> ((P) -> R).toPartialFunction(definedAt: (P) -> Boolean): PartialFunction<P, R> =
    PartialFunction(definedAt, this)


fun <P1, P2, R> ((P1, P2) -> R).partial1(p1: P1): (P2) -> R {
    return { p2 -> this(p1, p2) }
}

fun <P1, P2, R> ((P1, P2) -> R).partial2(p2: P2): (P1) -> R {
    return { p1 -> this(p1, p2) }
}

// 커링 추상화
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1: P1 ->
    { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } }
}

// 합성 함수
infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { gInput: G -> this(g(gInput)) }
}

val s: (Int) -> Int = { i: Int -> i * i }