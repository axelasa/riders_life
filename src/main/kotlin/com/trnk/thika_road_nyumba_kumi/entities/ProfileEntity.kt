package com.trnk.thika_road_nyumba_kumi.entities

import com.trnk.thika_road_nyumba_kumi.model.ProfileModel
import javax.persistence.*
import lombok.ToString
import java.util.Date

@Entity
@Table(name = "profile")
 class ProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long?,
    val createdAt:Date,
    val bloodGroup:String,
    var insurance:String,
    var insuranceId:String,
    var numberPlate:String,
    @Column(nullable = true)
    var roadLocation:String,
    var phoneNumber: String,
    @JoinColumn(name = "user_profile_id")
    @OneToOne(cascade = [CascadeType.DETACH])
    @ToString.Exclude
    val user: UserEntity,
    var updatedAt:Date
){
    companion object{
        private val now = Date()
        fun createProfile(profileModel: ProfileModel,user: UserEntity):ProfileEntity{
            val newProfile = ProfileEntity(null, createdAt = now, bloodGroup = profileModel.bloodGroup, insurance = profileModel.insurance, insuranceId = profileModel.insuranceId, numberPlate = profileModel.numberPlate, roadLocation = profileModel.roadLocation, phoneNumber = profileModel.phoneNumber, user = user, updatedAt = now)
            return newProfile
        }
    }
}
