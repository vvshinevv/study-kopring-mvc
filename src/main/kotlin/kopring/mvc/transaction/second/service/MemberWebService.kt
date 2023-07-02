package kopring.mvc.transaction.second.service

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kopring.mvc.transaction.second.domain.Member
import kopring.mvc.transaction.second.domain.MemberRepository

@Service
class MemberWebService(private val memberRepository: MemberRepository) {

    @Transactional(readOnly = true)
    @Cacheable(value = ["member_by_member_id"], keyGenerator = "idBasedKeyGenerator")
    fun <T> getMemberById(id: Long, transformer: (id: Member) -> T): T {
        return transformer(memberRepository.getReferenceById(id))
    }
}
