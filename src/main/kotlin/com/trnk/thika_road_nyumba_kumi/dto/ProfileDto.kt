package com.trnk.thika_road_nyumba_kumi.dto

import com.trnk.thika_road_nyumba_kumi.entities.ProfileEntity
import java.util.*

data class ProfileDto(
    val id:Long,
    val createdAt: Date,
    val bloodGroup:String,
    val insurance:String,
    val insuranceId:String,
    val numberPlate:String,
    val idNumber:String,
    val roadLocation:String,
    val phoneNumber: String,
    //val user: UserDto,
    val updatedAt: Date
){
    companion object{
        fun fromProfileEntity(p:ProfileEntity):ProfileDto{
            return ProfileDto(p.id!!,p.createdAt,p.bloodGroup,p.insurance,p.insuranceId,p.numberPlate,p.user.idNumber,p.roadLocation,p.phoneNumber,p.updatedAt)
        }
    }
}
