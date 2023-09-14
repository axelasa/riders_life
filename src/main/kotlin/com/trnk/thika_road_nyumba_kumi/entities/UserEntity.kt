package com.trnk.thika_road_nyumba_kumi.entities

import com.trnk.thika_road_nyumba_kumi.model.UserModel
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "user_data")
 class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long?,
    val createdAt:Date,
    val firstname:String,
    val lastname:String,
    val idNumber:String,
    val password:String,
    @JoinColumn(name = "profile_id")
    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var profile:ProfileEntity?,
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true )
    val emergencyContact:MutableSet<EmergencyContactEntity> = mutableSetOf(),
    val updatedAt:Date,


){
    companion object{
        private val now = Date()
        fun createNewUser(user: UserModel):UserEntity{
            val newUser = UserEntity(null, createdAt = now, firstname = user.firstname, lastname = user.lastname, idNumber = user.idNumber, password = user.password, updatedAt = now, emergencyContact = mutableSetOf(),profile = null,)
            return newUser
        }
    }
}
