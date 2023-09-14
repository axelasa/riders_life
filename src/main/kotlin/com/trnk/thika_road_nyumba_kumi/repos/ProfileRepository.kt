package com.trnk.thika_road_nyumba_kumi.repos

import com.trnk.thika_road_nyumba_kumi.entities.ProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProfileRepository :JpaRepository<ProfileEntity,Long>{
    fun findByBloodGroup(bloodGroup:String):Optional<ProfileEntity>
    fun findByNumberPlate(numberPlate:String):Optional<ProfileEntity>
    fun findByPhoneNumber(phoneNumber: String):Optional<ProfileEntity>
    fun findByRoadLocation(roadLocation:String):Optional<ProfileEntity>

}