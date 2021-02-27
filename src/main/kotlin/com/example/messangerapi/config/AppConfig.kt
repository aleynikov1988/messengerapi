package com.example.messangerapi.config

import com.example.messangerapi.components.AccountValidationInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppConfig : WebMvcConfigurer {
    lateinit var accountValidationInterceptor: AccountValidationInterceptor;

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(accountValidationInterceptor)
        super.addInterceptors(registry)
    }
}
