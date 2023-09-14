package com.trnk.thika_road_nyumba_kumi.entities

import com.trnk.thika_road_nyumba_kumi.model.EmergencyContactModel
import jakarta.persistence.*
import lombok.ToString
import java.util.*

@Entity
@Table(name="emergency_contact")
 class EmergencyContactEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long?,
    var createdAt: Date,
    var firstname:String,
    var lastname:String,
    var phoneNumber:String,
    val relations:String,
    var updatedAt: Date,
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH])
    @ToString.Exclude
    val user:UserEntity,
){
    companion object{
        private val now = Date()
        fun createEmergencyContact(ecModel: EmergencyContactModel,user: UserEntity):EmergencyContactEntity{
            val newEmergencyContact = EmergencyContactEntity(null, createdAt = now, firstname = ecModel.firstname, lastname = ecModel.lastname,phoneNumber = ecModel.phoneNumber, relations = ecModel.relations, updatedAt = now, user = user,)
            return newEmergencyContact
        }
    }
}