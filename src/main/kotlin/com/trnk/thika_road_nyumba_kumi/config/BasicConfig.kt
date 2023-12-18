package com.trnk.thika_road_nyumba_kumi.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.trnk.thika_road_nyumba_kumi.entities.UserEntity
import com.trnk.thika_road_nyumba_kumi.model.ApiResponse
import com.trnk.thika_road_nyumba_kumi.model.TokenResponse
import com.trnk.thika_road_nyumba_kumi.service.LoginService
import com.trnk.thika_road_nyumba_kumi.service.UserService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class BasicConfig{

    @Bean
    fun authenticationSuccessHandler(objectMapper: ObjectMapper, tokenService:TokenService): AuthenticationSuccessHandler {

        return AuthenticationSuccessHandler { _, response, authentication ->
            //token
            val token: TokenResponse = tokenService.createToken(authentication.name,authentication.details.toString())

            val result: ApiResponse = ApiResponse(HttpStatus.OK.value(),"Successful",token)

            response.contentType = MediaType.APPLICATION_JSON_VALUE

            objectMapper.writeValue(response.outputStream,result)
            //return response

        }
    }

    @Bean
    fun authenticationFailureHandler(objMapper: ObjectMapper):AuthenticationFailureHandler{

        return AuthenticationFailureHandler { request, response, exception ->

            val failure:ApiResponse = ApiResponse(HttpStatus.UNAUTHORIZED.value(), exception.message!!,"Sorry Wrong Credentials  provided")
            objMapper.writeValue(response.outputStream,failure)
        }
    }

    @Bean
    fun authenticationEntryPoint(objectMapper: ObjectMapper): AuthenticationEntryPoint {
        return AuthenticationEntryPoint { request: HttpServletRequest?, response: HttpServletResponse, ex: AuthenticationException ->
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            val apiResponse: ApiResponse = ApiResponse(HttpStatus.UNAUTHORIZED.value(),ex.message!!,null)

            objectMapper.writeValue(response.outputStream, apiResponse)
        }
    }

    @Bean
    fun accessDeniedHandler(objectMapper: ObjectMapper):AccessDeniedHandler{
        return AccessDeniedHandler { request: HttpServletRequest?, response: HttpServletResponse, ex: AccessDeniedException ->
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            val apiResponse: ApiResponse = ApiResponse(HttpStatus.UNAUTHORIZED.value(),ex.message!!,null)

            objectMapper.writeValue(response.outputStream, apiResponse)
        }
    }
}