package kopring.mvc.transaction

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InnerClass {

    @Transactional(readOnly = true)
    fun innerFunction(s: String): Result<String> {
        throw IllegalArgumentException("예외 발생")
    }
}
