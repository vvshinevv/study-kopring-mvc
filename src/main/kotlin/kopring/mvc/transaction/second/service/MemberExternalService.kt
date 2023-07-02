package kopring.mvc.transaction.second.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kopring.mvc.transaction.second.domain.Member


@Service
class MemberExternalService(private val memberWebService: MemberWebService) {

    @Transactional(readOnly = true)
    fun getMemberById(id: Long): Result<MemberDto> {
        return runCatching {
            memberWebService.getMemberById(id) {
                toMemberDto(it).getOrThrow()
            }
        }.onSuccess {
            log.info { "success :: ${it.memberName}" }
        }.onFailure {
            log.info { "failure!" }
        }
    }

    private fun toMemberDto(member: Member): Result<MemberDto> {
        return runCatching {
            require(member.memberName == "hongs") {
                "멤버의 이름(${member.memberName}이 `hongs`가 아닙니다."
            }
        }.map { MemberDto(member.id!!, member.memberName) }
    }

    companion object {
        private val log = KotlinLogging.logger { }
    }
}

data class MemberDto(val id: Long, val memberName: String)
