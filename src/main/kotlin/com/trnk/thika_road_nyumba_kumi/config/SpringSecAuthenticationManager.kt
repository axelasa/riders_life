package com.trnk.thika_road_nyumba_kumi.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import javax.security.auth.message.AuthException

@Configuration
class SpringSecAuthenticationManager(private val basicAuthProvider: BasicAuthenticationProvider): AuthenticationManager {
    override fun authenticate(authentication: Authentication?): Authentication {
        if (authentication == null ) throw AuthException("Authentication Required")
         if (basicAuthProvider.supports(authentication.javaClass)) return basicAuthProvider.authenticate(authentication)

             return authentication
    }
}