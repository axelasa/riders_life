package com.trnk.thika_road_nyumba_kumi.dto

import com.trnk.thika_road_nyumba_kumi.entities.EmergencyContactEntity
import java.util.*

data class EmergencyContactDto (
    val id:Long,
    val createdAt: Date,
    val firstname:String,
    val lastname:String,
    val phoneNumber:String,
    val relations:String,
    val updatedAt: Date,
    val idNumber:String,
){
    companion object{
        fun fromEmergencyContactEntity(e:EmergencyContactEntity):EmergencyContactDto{
            return EmergencyContactDto(e.id!!,e.createdAt,e.firstname,e.lastname,e.phoneNumber,e.relations,e.updatedAt,e.user.idNumber)
        }
    }
}