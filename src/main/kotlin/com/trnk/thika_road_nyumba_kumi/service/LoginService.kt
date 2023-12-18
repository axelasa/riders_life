package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.UserEntity
import org.springframework.security.core.userdetails.UserDetailsService

interface LoginService  {
    fun loginUser(idNumber:String,password:String):UserEntity
}