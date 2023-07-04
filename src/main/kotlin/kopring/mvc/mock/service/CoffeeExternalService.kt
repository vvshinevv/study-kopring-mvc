package kopring.mvc.mock.service

import kopring.mvc.mock.domain.Coffee
import org.springframework.stereotype.Service

@Service
class CoffeeExternalService(private val coffeeWebService: CoffeeWebService) {

    fun getCoffee(id: Long) {
        return coffeeWebService.getCoffeeById(id) { toCoffeeDto(it) }
    }

    private fun toCoffeeDto(coffee: Coffee): CoffeeDto {
        return CoffeeDto(coffee.id!!, coffee.coffeeName)
    }
}

data class CoffeeDto(val id: Long, val coffeeName: String)
