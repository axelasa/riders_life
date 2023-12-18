package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.UserEntity
import com.trnk.thika_road_nyumba_kumi.exceptions.ControllerExceptionHandler
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LoginServiceImpl(private val userService:UserService) : LoginService {

    override fun loginUser(idNumber: String, password: String): UserEntity {
        val user = userService.getUserByIdNumber(idNumber)
        if (user.isEmpty){
            throw ControllerExceptionHandler.unAuthorized("wrong Id number")
        }
        if (user.get().userPassword != password){
            throw ControllerExceptionHandler.unAuthorized("Wrong Credentials")
        }
        return user.get()
    }
}