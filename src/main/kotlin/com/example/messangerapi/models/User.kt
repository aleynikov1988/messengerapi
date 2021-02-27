package com.example.messangerapi.models

import com.example.messangerapi.constants.AccountStatus
import com.example.messangerapi.listeners.UserListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@EntityListeners(UserListener::class)
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    @Column(unique = true)
    @Size(min = 3)
    var username: String = "",
    @Size(min = 11)
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$")
    var phoneNumber: String = "",
    @Size(min = 6, max = 12)
    var password: String = "",
    var status: String = "",
    @Pattern(regexp = "\\A(activated|deactivated)\\z")
    var accountStatus: String = AccountStatus.ACTIVATED.status,
    @DateTimeFormat
    var createdAt: Date = Date.from(Instant.now())
) {
    @OneToMany(mappedBy = "sender", targetEntity = Message::class)
    private var sentMessages: Collection<Message>? = null
    @OneToMany(mappedBy = "recipient", targetEntity = Message::class)
    private var receivedMessages: Collection<Message>? = null
}
