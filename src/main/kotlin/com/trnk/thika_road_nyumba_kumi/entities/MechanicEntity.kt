package com.trnk.thika_road_nyumba_kumi.entities

import com.trnk.thika_road_nyumba_kumi.model.MechanicModel
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "mechanics")
data class MechanicEntity (
    @Id
    @GeneratedValue
    val id:Long,
    val createdAt:Date,
    val firstname:String,
    val lastname:String,
    val phoneNumber:String,
    val locationName:String,
    val latitude:String,
    val longitude:String,
    val updatedAt:Date
){
    companion object{
        private val now = Date()
        fun createNewMechanic(mechanicModel: MechanicModel){

        }
    }
}