package kopring.mvc.mock.service

import com.ninjasquad.springmockk.MockkClear
import com.ninjasquad.springmockk.SpykBean
import io.mockk.verify
import kopring.mvc.mock.domain.Coffee
import kopring.mvc.mock.domain.CoffeeRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CoffeeExternalServiceTest @Autowired constructor(
    private val coffeeExternalService: CoffeeExternalService,
    private val coffeeRepository: CoffeeRepository
) {

    @SpykBean(clear = MockkClear.BEFORE)
    private lateinit var coffeeService: CoffeeWebService

    @Test
    fun getCoffeeTest() {
        // given
        val americano = coffeeRepository.save(Coffee(null, "아메리카노"))
        val latte = coffeeRepository.save(Coffee(null, "아이스라떼"))

        // when
        coffeeExternalService.getCoffee(americano.id!!)

        // then
        verify(exactly = 1) {
            coffeeService.getCoffeeById(americano.id!!, any())
        }

        verify(exactly = 0) {
            coffeeService.getCoffeeById(latte.id!!) {}
        }
    }
}


