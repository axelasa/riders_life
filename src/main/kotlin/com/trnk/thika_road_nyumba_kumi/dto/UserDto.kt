package com.trnk.thika_road_nyumba_kumi.dto

import com.trnk.thika_road_nyumba_kumi.entities.UserEntity
import java.util.*

data class UserDto(
    val id:Long,
    val createdAt: Date,
    val firstname:String,
    val lastname:String,
    val idNumber:String,
    val password:String,
    val updatedAt: Date,
    val profile:ProfileDto?,
    val emergencyContact:Set<EmergencyContactDto>
){
    companion object{
        fun fromUserEntity(u:UserEntity):UserDto{
            val data = u.emergencyContact.map { EmergencyContactDto.fromEmergencyContactEntity(it) }.toSet()
            return UserDto(u.id!!,u.createdAt,u.firstname,u.lastname,u.idNumber,u.password,u.updatedAt, if (u.profile !=null) ProfileDto.fromProfileEntity(u.profile)else null,data)
        }
    }
}
