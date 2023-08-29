package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.ProfileEntity
import com.trnk.thika_road_nyumba_kumi.model.ProfileModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateProfile
import java.util.Optional

interface ProfileService {
    fun createProfile(profileModel: ProfileModel):ProfileEntity
    fun getProfileByBloodGroup(bloodGroup:String):Optional<ProfileEntity>
    fun getProfileByNumberPlate(numberPlate:String):Optional<ProfileEntity>
    fun getProfileByRoadLocation(roadLocation:String):Optional<ProfileEntity>
    fun updateProfile(updateProfile: UpdateProfile):ProfileEntity
}