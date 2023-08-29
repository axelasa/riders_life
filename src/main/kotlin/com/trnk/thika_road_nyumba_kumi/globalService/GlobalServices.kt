package com.trnk.thika_road_nyumba_kumi.globalService

import com.trnk.thika_road_nyumba_kumi.service.EmergencyContactService
import com.trnk.thika_road_nyumba_kumi.service.ProfileService
import com.trnk.thika_road_nyumba_kumi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GlobalServices {
    companion object{
        lateinit var userService:UserService
        lateinit var profileService:ProfileService
        lateinit var emergencyContactService:EmergencyContactService
        //lateinit var mechanicService:MechanicService
    }
    @Autowired
    fun setUserService(userService: UserService){
        GlobalServices.userService = userService
    }
    @Autowired
    fun setProfileService(profileService: ProfileService){
        GlobalServices.profileService = profileService
    }
    @Autowired
    fun setEmergencyContactService(emergencyContactService: EmergencyContactService){
        GlobalServices.emergencyContactService = emergencyContactService
    }
}