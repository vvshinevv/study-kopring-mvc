package kopring.mvc.functionalprogramming.chapter02

import mu.KotlinLogging
import org.junit.jupiter.api.Test

class Chapter02Test {

    @Test
    fun exercise02_1() {
        fun String.hello() = "hello, $this";
        log.info { "world".hello() }
    }

    @Test
    fun exercise02_2() {
        val results = listOf(0, -1, 2).map { x ->
            when {
                x == 0 -> "zero"
                x > 0 -> "positive"
                else -> "negative"
            }
        }

        log.info { results }
    }

    @Test
    fun exercise02_3() {
        val kotlin = Kotlin()
        kotlin.printKotlin()
    }

    @Test
    fun exercise02_4() {
        val sample = Sample("1", "hong")
        log.info { sample.component1() }
    }


    companion object {
        private val log = KotlinLogging.logger { }

        interface Foo {
            fun printKotlin() {
                log.info { "Foo - kotlin" }
            }
        }

        interface Bar {
            fun printKotlin() {
                log.info { "Bar - kotlin" }
            }
        }

        class Kotlin : Foo, Bar {
            override fun printKotlin() {
                super<Foo>.printKotlin()
                super<Bar>.printKotlin()
            }
        }

        data class Sample(val key: String, val value: String)
    }
}