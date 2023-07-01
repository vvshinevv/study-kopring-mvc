package kopring.mvc.transaction

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OuterClass(private val innerClass: InnerClass) {

    @Transactional(readOnly = true)
    fun outerFunction(): Result<String> {
        return runCatching { "something" }
            .mapCatching { innerClass.innerFunction(it).getOrThrow() }
            .onFailure { println("실패!:: $it") }
            .onSuccess { println("성공!:: $it") }
    }
}
