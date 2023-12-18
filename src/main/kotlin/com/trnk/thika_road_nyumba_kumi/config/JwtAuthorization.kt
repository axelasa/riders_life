package com.trnk.thika_road_nyumba_kumi.config

import com.auth0.jwt.algorithms.Algorithm
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
class JWTConfig {
    @Bean
    fun algorithm(@Value("var.jwt") jwtSecret:String): Algorithm {
        return Algorithm.HMAC256(jwtSecret)
    }
}

class JwtAuthorization(private val username:String,private val authorities:MutableCollection<GrantedAuthority>):Authentication{
    override fun getName(): String {
        return username
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getCredentials(): Any {
        return Any()
    }

    override fun getDetails(): Any {
        return Any()
    }

    override fun getPrincipal(): Any {
        return username
    }

    override fun isAuthenticated(): Boolean {
        return true
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {

    }

}
