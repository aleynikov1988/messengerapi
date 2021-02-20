package com.example.messangerapi.repositories

import com.example.messangerapi.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findByPhoneNumber(phoneNumber: String): User?
}
