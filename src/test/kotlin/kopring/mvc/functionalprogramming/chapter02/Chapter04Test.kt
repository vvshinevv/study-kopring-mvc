package kopring.mvc.functionalprogramming.chapter02

import arrow.core.compose
import arrow.core.curried
import arrow.core.tail
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
        val curriedGcd2 = { m: Int, n: Int -> gcd(m, powerOfTwo(n)) }.curried()

        val composedGcdPower1 = curriedGcd2 compose powerOfTwo
//        log.info { composedGcdPower(25)(5) }
    }

    @Test
    fun exercise04_6() {
        tailrec fun <P1, P2, R> zipWith(
            func: (P1, P2) -> R,
            list1: List<P1>,
            list2: List<P2>,
            acc: List<R> = listOf()
        ): List<R> = when {
            list1.isEmpty() || list2.isEmpty() -> acc
            else -> {
                val zipList = acc + listOf(func(list1.first(), list2.first()))
                zipWith(func, list1.tail(), list2.tail(), zipList)
            }
        }
    }

    @Test
    fun exercise04_7_1() {
        fun callback1(): (String) -> String {
            println("callback1")
            return { it }
        }

        fun callback2(): (String) -> (String) -> String {
            println("callback2")
            return { callback1() }
        }

        fun callback3(): (String) -> (String) -> (String) -> String {
            println("callback3")
            return { callback2() }
        }

        fun callback4(): (String) -> (String) -> (String) -> (String) -> String {
            println("callback4")
            return { callback3() }
        }

        val callback5: (String) -> (String) -> (String) -> (String) -> (String) -> String = {
            callback4()
        }

        log.info { callback5("5")("4")("3") }
    }

    @Test
    fun exercise04_7() {
        val callback: (String) -> (String) -> (String) -> (String) -> (String) -> String = { v1 ->
            { v2 ->
                { v3 ->
                    { v4 ->
                        { v5 ->
                            v1 + v2 + v3 + v4 + v5
                        }
                    }
                }
            }
        }

        val middle = callback("prefix")(":")
        log.info { middle("1")("2") }
    }

    @Test
    fun exercise04_8() {
        val result = object : Callback1 {
            override fun callback(x1: String): Callback2 {
                println("Callback1")
                return object : Callback2 {
                    override fun callback(x2: String): Callback3 {
                        println("Callback2")
                        return object : Callback3 {
                            override fun callback(x3: String): Callback4 {
                                println("Callback3")
                                return object : Callback4 {
                                    override fun callback(x4: String): Callback5 {
                                        println("Callback4")
                                        return object : Callback5 {
                                            override fun callback(x5: String): String {
                                                println("Callback5")
                                                return x1 + x2 + x3 + x4 + x5
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        val middle = result.callback("prefix").callback(":")
        log.info { middle.callback("3").callback("4") }
    }

    companion object {
        private val log = KotlinLogging.logger { }


        interface Callback5 {
            fun callback(x5: String): String
        }

        interface Callback4 {
            fun callback(x4: String): Callback5
        }

        interface Callback3 {
            fun callback(x3: String): Callback4
        }

        interface Callback2 {
            fun callback(x2: String): Callback3
        }

        interface Callback1 {
            fun callback(x1: String): Callback2
        }
    }
}