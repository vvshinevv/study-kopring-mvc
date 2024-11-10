package kopring.mvc.functionalprogramming.chapter02

import mu.KotlinLogging
import org.junit.jupiter.api.Test

class Chapter01Test {

    @Test
    fun chapter01_1() {
        val infiniteValue = generateSequence(0) { it + 5 }
        val finiteValue = listOf(1, 2, 3, 4, 5)

        val taken = infiniteValue.take(5)
        log.info { taken }
    }

    companion object {
        private val log = KotlinLogging.logger { }
    }
}