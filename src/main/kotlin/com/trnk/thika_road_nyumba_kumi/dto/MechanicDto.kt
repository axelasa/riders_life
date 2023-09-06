package com.trnk.thika_road_nyumba_kumi.dto

import com.trnk.thika_road_nyumba_kumi.entities.MechanicEntity
import java.util.*

data class MechanicDto (
    val id:Long?,
    val createdAt: Date,
    val firstname:String,
    val lastname:String,
    val username:String,
    val phoneNumber:String,
    val locationName:String,
    val latitude:Double,
    val longitude:Double,
    val updatedAt: Date
){
    companion object{
        fun fromMechanicEntity(m:MechanicEntity):MechanicDto{
            return MechanicDto(m.id,m.createdAt,m.firstname,m.lastname,m.username,m.phoneNumber,m.locationName,m.latitude,m.longitude,m.updatedAt)
        }
    }
}