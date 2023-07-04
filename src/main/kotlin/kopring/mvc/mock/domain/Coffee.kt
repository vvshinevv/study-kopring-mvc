package kopring.mvc.mock.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "coffee")
@Entity
class Coffee(
    @field:[Id GeneratedValue(strategy = GenerationType.IDENTITY)]
    var id: Long?,
    @field:[Column(name = "coffeeName")]
    var coffeeName: String
)
