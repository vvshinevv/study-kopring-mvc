package kopring.mvc.funtional.chapter08

fun main() {
    // fmap test
    println(AJust(10).fmap { it + 10 })     // AJust(20)
    println(ANothing.fmap { it: Int -> it + 10 })   // ANothing

    // pure test
    println(AMaybe.pure(10))                // AJust(10)
//    println(AJust(0).pure(2))
//    // apply test
//    println(AJust(10) apply AJust { x: Int -> x * 2 })   // AJust(20)
//    println(ANothing apply AJust { x: Int -> x * 2 })            // ANothing
//    println(AJust(10).apply(AJust { x: Int -> x * 2 }))  // AJust(20)
//
//    // applicative style programming test
//    println(AMaybe.pure(10)
//        apply AJust { x: Int -> x * 2 }
//        apply AJust { x: Int -> x + 10 })    // AJust(30)

    //    println(AMaybe.pure({ x: Int -> x * 2 })
    //            apply AJust(5)
    //            apply AJust(10))    // compile error

}

fun <A, B> apply(ff: AMaybe<(A) -> B>): AMaybe<B> {
    return ANothing
}

sealed class AMaybe<out A> : Applicative<A> {

    companion object {
        fun <V> pure(value: V): Applicative<V> = AJust("aasdfasdfadf").pure(value)
    }

    override fun <V> pure(value: V): Applicative<V> = AJust(value)

    abstract override fun <B> apply(ff: Applicative<(A) -> B>): AMaybe<B>
}

data class AJust<out A>(val value: A) : AMaybe<A>() {

    override fun toString(): String = "AJust($value)"

    override fun <B> apply(ff: Applicative<(A) -> B>): AMaybe<B> = when (ff) {
        is AJust -> fmap(ff.value)
        else -> ANothing
    }

    override fun <B> fmap(f: (A) -> B): AMaybe<B> = AJust(f(value))
}

object ANothing : AMaybe<Nothing>() {

    override fun toString(): String = "ANothing"

    override fun <B> apply(ff: Applicative<(Nothing) -> B>): AMaybe<B> = ANothing

    override fun <B> fmap(f: (Nothing) -> B): AMaybe<B> = ANothing
}