package kopring.mvc.functionalprogramming.chapter02

import arrow.core.compose
import arrow.core.curried
import kopring.mvc.funtional.chapter04.PartialFunction
import kopring.mvc.funtional.chapter04.toPartialFunction
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class Chapter04Test {

    @Test
    fun exercise04_1() {
        fun twice(x: Int) = x * 2

        // x 는 twice 함수의 부분함수이다.
        fun partialTwice(x: Int) = if (x < 100) x * 2 else throw IllegalArgumentException()
    }

    @Test
    fun exercise04_2() {
        val condition: (Int) -> Boolean = { it in 1..3 }
        val body: (Int) -> String = {
            when (it) {
                1 -> "One"
                2 -> "Two"
                3 -> "Three"
                else -> throw IllegalArgumentException()
            }
        }

        val oneTwoThree = PartialFunction(condition, body)
        if (oneTwoThree.isDefinedAt(3)) {
            log.info { oneTwoThree(3) }
        } else {
            log.info { "isDefinedAt(x) return false" }
        }
    }

    @Test
    fun exercise04_3() {
        val condition: (Int) -> Boolean = { it.rem(2) == 0 }
        val body: (Int) -> String = { "$it is even" }

        val isEven = body.toPartialFunction(condition)

        if (isEven.isDefinedAt(100)) {
            log.info { isEven(100) }
        } else {
            log.info { "isDefinedAt(x) return false" }
        }
    }

    @Test
    fun exercise04_4() {
        // 커링
        fun multiThree(a: Int) = { b: Int -> { c: Int -> a * b * c } }

        val partial1 = multiThree(1)
        val partial2 = partial1(2)
        val partial3 = partial2(3)

        log.info { partial3 }
        log.info { multiThree(1)(2)(3) }
    }

    @Test
    fun exercise04_5() {
        tailrec fun gcd(m: Int, n: Int): Int = when (n) {
            0 -> m
            else -> gcd(n, m % n)
        }

        tailrec fun power(x: Double, n: Int, acc: Double = 1.0): Double = when (n) {
            0 -> acc
            else -> power(x, n - 1, x * acc)
        }

        val powerOfTwo = { x: Int -> power(x.toDouble(), 2).toInt() }
        val curriedGcd1 = ::gcd.curried()
        val curriedGcd2 = { m: Int, n: Int -> gcd(m, powerOfTwo(n))}.curried()

        val composedGcdPower1 = curriedGcd2 compose powerOfTwo
//        log.info { composedGcdPower(25)(5) }
    }

    companion object {
        private val log = KotlinLogging.logger { }
    }
}