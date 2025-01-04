package kopring.mvc.functionalprogramming.chapter02

import kopring.mvc.funtional.chapter04.compose
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class Chapter07Test {

    @Test
    fun chapter07_1() {
        val some1 = Some1()
        val some2 = Some2()
        println(some1.equal(5, 5))
        println(some2.equal(5, 5))


        sayEq(5, 5, some1)
    }

    @Test
    fun chaper07_2() {
        val list = listOf(1, 2, 3)
        val f = { x: Int -> x + 1 }
        val g = { x: Int -> x * 2 }

        // Function references f1 and g1 can also be used instead of f and g
        val composed = list.map(::f1 compose ::g1) // fmap(f compose g)
        println("====")
        val sequential = list.map(::g1).map(::f1) // fmap(f).fmap(g)

        println(composed)  // [3, 5, 7]
        println(sequential) // [3, 5, 7]
    }

    private fun f1(x: Int): Int {
        println("f1  $x")
        return x + 1
    }

    private fun g1(x: Int): Int {
        println("g1  $x")
        return x * 2
    }

    @Test
    fun chapter07_3() {
        val list = listOf(1, 2, 3)
        list.map { x -> x * 2 }
    }

    companion object {
        private val log = KotlinLogging.logger { }
    }
}

interface Eq<T> {
    fun equal(x: T, y: T): Boolean
    fun notEqual(x: T, y: T): Boolean
}

class Some1 : Eq<Int> {
    override fun equal(x: Int, y: Int): Boolean = x == y
    override fun notEqual(x: Int, y: Int): Boolean = x != y
}

class Some2 : Eq<Int> {
    override fun equal(x: Int, y: Int): Boolean = x == y
    override fun notEqual(x: Int, y: Int): Boolean = x != y
}

fun <T> sayEq(x: T, y: T, eq: Eq<T>) {
    println(eq.equal(x, y))
}

interface Functor<out A> {
    fun <B> fmap(f: (A) -> B): Functor<B>
}

sealed class Maybe<out A> : Functor<A> {
    abstract override fun toString(): String

    abstract override fun <B> fmap(f: (A) -> B): Functor<B>
}
