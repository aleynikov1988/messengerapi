package com.example.messangerapi.components

import com.example.messangerapi.constants.AccountStatus
import com.example.messangerapi.exceptions.UserDeactivatedException
import com.example.messangerapi.models.User
import com.example.messangerapi.repositories.UserRepository
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AccountValidationInterceptor(val repository: UserRepository) : HandlerInterceptor {
    @Throws(UserDeactivatedException::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val princial: Principal? = request.userPrincipal

        if (princial != null) {
            val user = repository.findByUsername(princial.name) as User

            if (user.accountStatus == AccountStatus.DEACTIVATED.status) {
                throw UserDeactivatedException("User has been deactivated")
            }
        }
        return super.preHandle(request, response, handler)
    }
}
