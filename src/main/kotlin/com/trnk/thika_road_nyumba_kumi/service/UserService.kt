package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.UserEntity
import com.trnk.thika_road_nyumba_kumi.model.UpdateUserModel
import com.trnk.thika_road_nyumba_kumi.model.UserModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.Optional

interface UserService {
    fun createUser(user:UserModel):UserEntity
    fun searchUserByIdNumber(idNumber:String,pageable: Pageable):Page<UserEntity>
    fun getUserByIdNumber(idNumber: String):Optional<UserEntity>
    fun getUserById(id:Long):Optional<UserEntity>

    fun updateUser(userDetails:UpdateUserModel):UserEntity
    fun getAllUsers():List<UserEntity>
}