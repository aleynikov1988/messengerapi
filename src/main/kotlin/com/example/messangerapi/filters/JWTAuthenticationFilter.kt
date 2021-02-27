package com.example.messangerapi.filters

import com.example.messangerapi.services.TokenAuthenticationService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import kotlin.jvm.Throws

class JWTAuthenticationFilter() : GenericFilterBean() {
    @Throws(
        IOException::class,
        ServletException::class
    )
    override fun doFilter(p0: ServletRequest, p1: ServletResponse, p2: FilterChain) {
        SecurityContextHolder.getContext()
            .authentication = TokenAuthenticationService.getAuthentication(p0 as HttpServletRequest)
        p2.doFilter(p0, p1)
    }
}
