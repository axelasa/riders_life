package com.trnk.thika_road_nyumba_kumi.entities

import com.trnk.thika_road_nyumba_kumi.model.UserModel
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "user_data")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long?,
    val createdAt:Date,
    val firstname:String,
    val lastname:String,
    val idNumber:String,
    val password:String,
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_profile_id")
    val profile:ProfileEntity?,
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "user")
    val emergencyContact:MutableSet<EmergencyContactEntity> = mutableSetOf(),
    val updatedAt:Date,


){
    companion object{
        private val now = Date()
        fun createNewUser(userModel: UserModel):UserEntity{
            val newUser = UserEntity(null, createdAt = now, firstname = userModel.firstname, lastname = userModel.lastname, idNumber = userModel.idNumber, password = userModel.password, profile = null, emergencyContact = mutableSetOf(), updatedAt = now)
            return newUser
        }

//        fun createNewUser(userModel: UserModel): UserEntity {
//
//        }
    }
}
