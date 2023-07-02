package kopring.mvc.transaction.second.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "member")
class Member(
    @field:[Id GeneratedValue(strategy = GenerationType.IDENTITY)]
    var id: Long?,
    @field:[Column(name = "member_name")]
    var memberName: String
)
