package com.example.messangerapi.services

import com.example.messangerapi.exceptions.UsernameNotFoundException
import com.example.messangerapi.repositories.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.ArrayList
import kotlin.jvm.Throws

@Service
class UserDetailsServiceImpl(val repository: UserRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = repository.findByUsername(username)
            ?: throw UsernameNotFoundException("username does not exist")

        return User(user.username, user.password, ArrayList<GrantedAuthority>())
    }
}
