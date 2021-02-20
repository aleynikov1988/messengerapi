package com.example.messangerapi.models

import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "conversation")
class Conversation(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    var sender: User? = null,
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    var recipient: User? = null,
    @DateTimeFormat
    var createdAt: Date = Date.from(Instant.now())
) {
    @OneToMany(mappedBy = "conversation", targetEntity = Message::class)
    private var messages: Collection<Message>? = null
}
