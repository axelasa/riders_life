package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.ProfileEntity
import com.trnk.thika_road_nyumba_kumi.exceptions.ControllerExceptionHandler
import com.trnk.thika_road_nyumba_kumi.model.ProfileModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateProfile
import com.trnk.thika_road_nyumba_kumi.repos.ProfileRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProfileServiceImpl(private val profileRepository: ProfileRepository,private val userService: UserService) : ProfileService {
    override fun createProfile(profileModel: ProfileModel): ProfileEntity {
        val existingProfile = getProfileByNumberPlate(profileModel.numberPlate)
        if (existingProfile.isEmpty) throw ControllerExceptionHandler.conflicts("This Number Plate Already Exists")

        val optionalUser = userService.getUserByIdNumber(profileModel.numberPlate)
        if (optionalUser.isEmpty) throw ControllerExceptionHandler.conflicts("User With ID ${profileModel.numberPlate} Does Not Exist")

        val user = optionalUser.get()
        val newProfile = ProfileEntity.createProfile(profileModel,user)

        return profileRepository.save(newProfile)
    }

    override fun getProfileByBloodGroup(bloodGroup: String): Optional<ProfileEntity> {
        return profileRepository.findByBloodGroup(bloodGroup)
    }

    override fun getProfileByNumberPlate(numberPlate: String): Optional<ProfileEntity> {
        return profileRepository.findByNumberPlate(numberPlate)
    }

    override fun getProfileByRoadLocation(roadLocation: String): Optional<ProfileEntity> {
        return profileRepository.findByRoadLocation(roadLocation)
    }

    override fun updateProfile(updateProfile: UpdateProfile): ProfileEntity {
        val  existingProfile = getProfileByNumberPlate(updateProfile.numberPlate)
        if (existingProfile.isEmpty) throw ControllerExceptionHandler.notFound("This profile data does not exist")

        val updateExistingProfile = existingProfile.get()
        if (updateProfile.insurance != null) updateExistingProfile.insurance = updateProfile.insurance
        if (updateProfile.insuranceId != null) updateExistingProfile.insuranceId = updateProfile.insuranceId
        if (updateProfile.phoneNumber != null) updateExistingProfile.phoneNumber = updateProfile.phoneNumber
       // if (updateProfile.roadLocation != null) updateExistingProfile.roadLocation = updateProfile.roadLocation

        updateExistingProfile.updatedAt = Date()

        return profileRepository.save(updateExistingProfile)
    }
}