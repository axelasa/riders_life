package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.ProfileEntity
import com.trnk.thika_road_nyumba_kumi.exceptions.ControllerExceptionHandler
import com.trnk.thika_road_nyumba_kumi.model.ProfileModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateProfile
import com.trnk.thika_road_nyumba_kumi.repos.ProfileRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class ProfileServiceImpl(private val profileRepository: ProfileRepository,private val userService: UserService) : ProfileService {
    override fun createProfile(profileModel: ProfileModel): ProfileEntity {
        val existingProfile = getProfileByNumberPlate(profileModel.numberPlate)
        if (existingProfile.isPresent) throw ControllerExceptionHandler.conflicts("This Number Plate Already Exists")

        val optionalUser = userService.getUserByIdNumber(profileModel.idNumber)
        if (optionalUser.isEmpty) throw ControllerExceptionHandler.conflicts("User With ID ${profileModel.idNumber} Does Not Exist")

        val user = optionalUser.get()

        val newProfile = ProfileEntity.createProfile(profileModel,user)

        val profile = profileRepository.save(newProfile)

        user.profile = newProfile

        return profile
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

    override fun getProfileByPhoneNUmber(phoneNumber: String): Optional<ProfileEntity> {
        return profileRepository.findByPhoneNumber(phoneNumber)
    }

    override fun updateProfile(updateProfile: UpdateProfile): ProfileEntity {
        val  existingProfile = getProfileByPhoneNUmber(updateProfile.phoneNumber)
        if (existingProfile.isEmpty) throw ControllerExceptionHandler.notFound("This profile data does not exist")

        val updateExistingProfile = existingProfile.get()
        if (updateProfile.insurance != null) updateExistingProfile.insurance = updateProfile.insurance
        if (updateProfile.insuranceId != null) updateExistingProfile.insuranceId = updateProfile.insuranceId
        if (updateProfile.numberPlate != null) updateExistingProfile.numberPlate = updateProfile.numberPlate
        //if (updateProfile.phoneNumber != null) updateExistingProfile.phoneNumber = updateProfile.phoneNumber
       // if (updateProfile.roadLocation != null) updateExistingProfile.roadLocation = updateProfile.roadLocation

        updateExistingProfile.updatedAt = Date()

        return profileRepository.save(updateExistingProfile)
    }

    override fun getAllProfiles(): List<ProfileEntity> {
        return profileRepository.findAll()
    }
}