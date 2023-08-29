package com.trnk.thika_road_nyumba_kumi.entities

import com.trnk.thika_road_nyumba_kumi.model.EmergencyContactModel
import jakarta.persistence.*
import lombok.ToString
import java.util.*

@Entity
@Table(name="emergency_contact")
data class EmergencyContactEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long?,
    val createdAt: Date,
    val firstname:String,
    val lastname:String,
    val idNumber:String,
    val phoneNumber:String,
    val relations:String,
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
    @ToString.Exclude
    val user:UserEntity,
    val updatedAt: Date
){
    companion object{
        private val now = Date()
        fun createEmergencyContact(ecModel: EmergencyContactModel,user: UserEntity):EmergencyContactEntity{
            val newEmergencyContact = EmergencyContactEntity(null, createdAt = now, firstname = ecModel.firstname, lastname = ecModel.lastname, idNumber = ecModel.idNumber ,phoneNumber = ecModel.phoneNumber, relations = ecModel.relations, user = user, updatedAt = now)
            return newEmergencyContact
        }
    }
}