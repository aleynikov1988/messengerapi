package com.example.messangerapi.filters

import com.example.messangerapi.security.AccountCredentials
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

class JWTLoginFilter(url: String, authManager: AuthenticationManager) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url)) {
    init {
        authenticationManager = authManager
    }

    @Throws(
        AuthenticationException::class,
        IOException::class,
        ServletException::class
    )
    override fun attemptAuthentication(p0: HttpServletRequest?, p1: HttpServletResponse?): Authentication {
        val credentials = ObjectMapper()
            .readValue(p0?.inputStream, AccountCredentials::class.java)

        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
            credentials.username,
            credentials.password,
            emptyList()
        ))
    }

    @Throws(
        IOException::class,
        ServletException::class
    )
    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        super.successfulAuthentication(request, response, chain, authResult)
    }
}
