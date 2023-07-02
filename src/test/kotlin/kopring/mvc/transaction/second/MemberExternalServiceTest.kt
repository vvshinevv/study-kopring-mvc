package kopring.mvc.transaction.second

import kopring.mvc.transaction.second.domain.Member
import kopring.mvc.transaction.second.domain.MemberRepository
import kopring.mvc.transaction.second.service.MemberExternalService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.UnexpectedRollbackException

@SpringBootTest
class MemberExternalServiceTest @Autowired constructor(
    private val memberExternalService: MemberExternalService,
    private val memberRepository: MemberRepository
) {

    @DisplayName("UnexpectedRollbackException 예외가 발생함")
    @Test
    fun transactionTest() {
        // given
        val member = Member(null, "I'm not hongs")
        val createdMember = memberRepository.save(member)

        // when, then
        assertThrows<UnexpectedRollbackException> {
            memberExternalService.getMemberById(createdMember.id!!)
        }
    }
}
