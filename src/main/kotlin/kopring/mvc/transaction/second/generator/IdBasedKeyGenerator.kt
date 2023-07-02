package kopring.mvc.transaction.second.generator

import arrow.core.flatMap
import kopring.mvc.transaction.second.domain.Member
import kopring.mvc.transaction.second.domain.MemberRepository
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.cache.interceptor.SimpleKey
import org.springframework.cache.interceptor.SimpleKeyGenerator
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.lang.reflect.Method
import java.util.Optional

@Component("idBasedKeyGenerator")
class IdBasedKeyGenerator(private val repository: MemberRepository) : KeyGenerator {

    @Transactional(readOnly = true)
    override fun generate(target: Any, method: Method, vararg params: Any?): Any {
        return runCatching { params[0] as Long }
            .mapCatching(repository::findById)
            .flatMap(::generateKey)
    }

    fun generateKey(entity: Optional<Member>, vararg params: Any): Result<Any> {
        return runCatching {
            entity.map { SimpleKeyGenerator.generateKey(it.id!!, params) }
                .orElse(SimpleKey.EMPTY)
        }
    }
}
