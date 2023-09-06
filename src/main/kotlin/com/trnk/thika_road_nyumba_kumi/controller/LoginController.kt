package com.trnk.thika_road_nyumba_kumi.controller

import com.trnk.thika_road_nyumba_kumi.dto.UserDto
import com.trnk.thika_road_nyumba_kumi.globalService.GlobalServices.Companion.loginService
import com.trnk.thika_road_nyumba_kumi.model.ApiResponse
import com.trnk.thika_road_nyumba_kumi.model.LoginModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("trnk")
class LoginController {
    @PostMapping("sign_in")
    fun logInUser(@RequestBody loginModel: LoginModel):ResponseEntity<Any>{
        val user = loginService.loginUser(loginModel.username,loginModel.password)
        val response = ApiResponse(HttpStatus.CONFLICT.value()," Username and Password is Required",null)
        if (user.idNumber.isEmpty() || user.password.isEmpty()){
            return ResponseEntity(response,HttpStatus.CONFLICT)
        }
        val result = ApiResponse(HttpStatus.OK.value(),"Login Successful",UserDto.fromUserEntity(user))
        return ResponseEntity(result,HttpStatus.OK)
    }
}