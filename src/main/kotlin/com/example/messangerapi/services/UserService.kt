package com.example.messangerapi.services

import com.example.messangerapi.models.User

interface UserService {
    fun listUsers(currentUser: User): List<User>
    fun usernameExist(username: String): Boolean
    fun getUserByUsername(username: String): User?
    fun getUserById(id: Long): User?
    fun attemptRegistration(user: User): User
    fun updateUserStatus(user: User, status: String): User
}
