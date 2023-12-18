package com.trnk.thika_road_nyumba_kumi.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
class AuthorizationFilter(private val algorithm:Algorithm): OncePerRequestFilter()  {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        val token:String = authHeader.replace("Bearer ","")
        val jwtDecode:DecodedJWT = JWT.require(algorithm).build().verify(token)
        val username:String = jwtDecode.subject
        val authorities:MutableList<GrantedAuthority> = mutableListOf()
        val role:String = jwtDecode.getClaim("role").asString()
        authorities.add(GrantedAuthority{"ROLE_$role"})

        val  authorization = JwtAuthorization(username = username, authorities =authorities)

        SecurityContextHolder.getContext().authentication = authorization

        filterChain.doFilter(request,response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val authorizationHeader:String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        if(authorizationHeader.isNullOrEmpty()) return true

        if (!authorizationHeader.startsWith("Bearer")) return true

        return false
    }
}