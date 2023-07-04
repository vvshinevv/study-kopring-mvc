package kopring.mvc.mock.service

import kopring.mvc.mock.domain.Coffee
import kopring.mvc.mock.domain.CoffeeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoffeeWebService(private val coffeeRepository: CoffeeRepository) {

    @Transactional
    fun <T> getCoffeeById(id: Long, transformer: (coffee: Coffee) -> T): T {
        return transformer(coffeeRepository.getReferenceById(id))
    }
}
