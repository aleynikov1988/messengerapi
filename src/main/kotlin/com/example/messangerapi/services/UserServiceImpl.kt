package com.example.messangerapi.services

import com.example.messangerapi.exceptions.InvalidUserIdException
import com.example.messangerapi.exceptions.UserStatusEmptyException
import com.example.messangerapi.exceptions.UsernameUnavailableException
import com.example.messangerapi.models.User
import com.example.messangerapi.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val repository: UserRepository) : UserService {
    override fun listUsers(currentUser: User): List<User> {
        return repository.findAll().mapTo(ArrayList(), { it }).filter { it != currentUser }
    }

    override fun usernameExist(username: String): Boolean {
        return repository.findByUsername(username) != null
    }

    override fun getUserByUsername(username: String): User? {
        val user = repository.findByUsername(username)
        blurPassword(user)
        return user
    }

    @Throws(InvalidUserIdException::class)
    override fun getUserById(id: Long): User? {
        val record = repository.findById(id)

        if (record.isPresent) {
            val user = record.get()
            blurPassword(user)
            return user
        }
        throw InvalidUserIdException("User does not exists")
    }

    @Throws(UsernameUnavailableException::class)
    override fun attemptRegistration(user: User): User {
        if (!usernameExist(user.username)) {
            val newUser = User()
            newUser.username = user.username
            newUser.phoneNumber = user.phoneNumber
            newUser.password = user.password
            repository.save(newUser)
            blurPassword(user)
            return newUser
        }
        throw UsernameUnavailableException("Username is unavailable")
    }

    private fun blurPassword(user: User?) {
        user?.password = "xxx xxxx xxx"
    }

    @Throws(UserStatusEmptyException::class)
    override fun updateUserStatus(user: User, status: String): User {
        if (status.isEmpty()) {
            throw UserStatusEmptyException("User is empty")
        }
        user.status = status
        repository.save(user)
        return user
    }
}
