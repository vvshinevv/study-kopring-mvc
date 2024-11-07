package kopring.mvc.transaction.first

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OuterClassTest @Autowired constructor(
    private val outerClass: OuterClass
) {
    @Test
    fun test() {
        val result = outerClass.outerFunction()
        assertThat(result.isFailure).isTrue()
    }
}
