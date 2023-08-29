package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.UserEntity

interface LoginService {
    fun loginUser(idNumber:String,password:String):UserEntity
}