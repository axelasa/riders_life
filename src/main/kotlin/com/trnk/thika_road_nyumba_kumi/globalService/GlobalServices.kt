package com.trnk.thika_road_nyumba_kumi.globalService

import com.trnk.thika_road_nyumba_kumi.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GlobalServices {
    companion object{
        lateinit var userService:UserService
        lateinit var loginService:LoginService
        lateinit var profileService:ProfileService
        lateinit var emergencyContactService:EmergencyContactService
        lateinit var mechanicService: MechanicService
        lateinit var merchantService: MerchantService
    }
    @Autowired
    fun setUserService(userService: UserService){
        GlobalServices.userService = userService
    }
    @Autowired
    fun setLoginService(loginService: LoginService){
        GlobalServices.loginService = loginService
    }
    @Autowired
    fun setProfileService(profileService: ProfileService){
        GlobalServices.profileService = profileService
    }
    @Autowired
    fun setEmergencyContactService(emergencyContactService: EmergencyContactService){
        GlobalServices.emergencyContactService = emergencyContactService
    }
    @Autowired
    fun setMechanicService(mechanicService: MechanicService){
        GlobalServices.mechanicService = mechanicService
    }
    @Autowired
    fun setMerchantService(merchantService: MerchantService){
        GlobalServices.merchantService = merchantService
    }
}