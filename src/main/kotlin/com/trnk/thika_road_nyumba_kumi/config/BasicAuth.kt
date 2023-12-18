package com.trnk.thika_road_nyumba_kumi.config

import com.trnk.thika_road_nyumba_kumi.exceptions.AuthExceptions
import com.trnk.thika_road_nyumba_kumi.service.LoginServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

//BasicAuthentication is => The object that gets passed by authentication manager to authenticate a user
class BasicAuthentication(private val username:String, private var authe:Boolean, private val password:String,  var roleName:String?):Authentication{

    override fun getName(): String {
       return username
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableSetOf()
    }

    override fun getCredentials(): String {
        return password
    }

    override fun getDetails(): Any? {
        return roleName
    }

    override fun getPrincipal(): Any {
        return username
    }

    override fun isAuthenticated(): Boolean {
       return authe
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        authe = isAuthenticated
    }

    override fun toString(): String {
        return "BasicAuthentication(username='$username', authe=$authe, password='$password')"
    }

}

@Configuration
class BasicAuthenticationProvider(var userDetailsService:UserDetailsService): AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        //here get supported authentication

        val auth:BasicAuthentication = authentication as BasicAuthentication

        //validate the authentication

         val username:String = auth.name
        val password: String = auth.credentials
         val userDetails:UserDetails = userDetailsService.loadUserByUsername(username)

        //verify password (stored password)
         val storedPassword = userDetails.password
        //password attempt
        val passAttempt =  auth.credentials

        val validPass:Boolean = passAttempt == storedPassword
        print("=========== $username:$storedPassword => $validPass ==============")
        if (!validPass) throw AuthExceptions("Invalid password")

        //return authenticated authentication
        auth.roleName = userDetails.authorities.stream().findFirst().map {
            it.authority
        }.orElse(null)
        auth.isAuthenticated = true

        return auth

    }

    override fun supports(authentication: Class<*>?): Boolean {
     return authentication == BasicAuthentication::class.java

    }
}


