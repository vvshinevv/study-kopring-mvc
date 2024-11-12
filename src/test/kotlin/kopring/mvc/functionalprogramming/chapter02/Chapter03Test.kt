package kopring.mvc.functionalprogramming.chapter02

import arrow.core.tail
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class Chapter03Test {

    @Test
    fun exercise03_1() {
        log.info { fiboRecursion(6) }
    }

    private fun fiboRecursion(n: Int): Int = when (n) {
        0 -> 0
        1 -> 1
        else -> fiboRecursion(n - 1) + fiboRecursion(n - 2)
    }

    @Test
    fun exercise03_2() {
        log.info { trampoline(even(2)) }
    }

    private fun even(n: Int): Bounce<Boolean> = when (n) {
        0 -> Done(true)
        else -> More { odd(n - 1) }
    }

    private fun odd(n: Int): Bounce<Boolean> = when (n) {
        0 -> Done(false)
        else -> More { even(n - 1) }
    }

    @Test
    fun exercise03_3() {
        log.info { powerset(setOf(1, 2, 3), setOf(setOf())) }
    }

    tailrec fun <T> powerset(s: Set<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
        s.isEmpty() -> acc
        else -> powerset(s.tail().toSet(), acc + acc.map { it + s.last() })
    }


    companion object {
        private val log = KotlinLogging.logger { }

        sealed class Bounce<A>
        data class Done<A>(val result: A) : Bounce<A>()
        data class More<A>(val thunk: () -> Bounce<A>) : Bounce<A>()

        tailrec fun <A> trampoline(bounce: Bounce<A>): A = when (bounce) {
            is Done -> bounce.result
            is More -> trampoline(bounce.thunk())
        }
    }
}