package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.UserEntity
import com.trnk.thika_road_nyumba_kumi.exceptions.ControllerExceptionHandler
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.ModuleLayer.Controller
@Service
@Transactional
class LoginServiceImpl(private val userService:UserService) : LoginService {
    override fun loginUser(idNumber: String, password: String): UserEntity {
        val user = userService.getUserByIdNumber(idNumber)
        if (user.isEmpty){
            throw ControllerExceptionHandler.unAuthorized("wrong Id number")
        }
        if (user.get().password != password){
            throw ControllerExceptionHandler.unAuthorized("Wrong Credentials")
        }
        return user.get()
    }
}