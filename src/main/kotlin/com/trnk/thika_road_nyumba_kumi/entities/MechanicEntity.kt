package com.trnk.thika_road_nyumba_kumi.entities

import com.trnk.thika_road_nyumba_kumi.model.MechanicModel
import javax.persistence.*
import java.util.Date

@Entity
@Table(name = "mechanics")
 class MechanicEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long?,
    val createdAt:Date,
    var firstname:String,
    var lastname:String,
    val username:String,
    var phoneNumber:String,
    var locationName:String,
    var latitude:Double,
    var longitude:Double,
    var location:Double,
    var updatedAt:Date
){
    companion object{
        private val now = Date()
        fun createNewMechanic(mechanicModel: MechanicModel):MechanicEntity{
            val newMec = MechanicEntity(null, now,mechanicModel.firstname,mechanicModel.lastname,mechanicModel.username,mechanicModel.phoneNumber,mechanicModel.locationName,mechanicModel.latitude,mechanicModel.longitude, mechanicModel.location,now)
            return newMec
        }
    }
}