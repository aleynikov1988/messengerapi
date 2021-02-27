package com.example.messangerapi.services

import org.springframework.security.core.userdetails.UserDetails

interface UserDetailsService {
    fun loadUserByUsername(username: String): UserDetails
}
