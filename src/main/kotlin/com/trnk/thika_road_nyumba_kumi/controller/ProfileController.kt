package com.trnk.thika_road_nyumba_kumi.controller

import com.trnk.thika_road_nyumba_kumi.dto.ProfileDto
import com.trnk.thika_road_nyumba_kumi.globalService.GlobalServices.Companion.profileService
import com.trnk.thika_road_nyumba_kumi.model.ApiResponse
import com.trnk.thika_road_nyumba_kumi.model.ProfileModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateProfile
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/trnk")
class ProfileController {
    @PostMapping("create_profile")
    fun createAProfile(@RequestBody profile:ProfileModel):ResponseEntity<Any>{
        val newProfile = profileService.createProfile(profile)
        val result = ApiResponse(HttpStatus.OK.value(),"Profile Created Successfully",ProfileDto.fromProfileEntity(newProfile))
        return ResponseEntity(result,HttpStatus.OK)
    }
    @PutMapping("update_profile")
    fun updateProfile(@RequestBody updateProfile: UpdateProfile):ResponseEntity<Any>{
        val existingProfile = profileService.updateProfile(updateProfile)
        val result = ApiResponse(HttpStatus.OK.value(),"Profile Updated Successfully",ProfileDto.fromProfileEntity(existingProfile))
        return ResponseEntity(result,HttpStatus.OK)
    }
    @GetMapping
    fun getSingleProfile(@RequestParam("numberPlate", required = true)numberPlate:String):ResponseEntity<Any>{
      val response = ApiResponse(HttpStatus.NOT_FOUND.value(),"Profile $numberPlate Not Found",null)
        val optionalProfile = profileService.getProfileByNumberPlate(numberPlate)
        if (optionalProfile.isEmpty){
            return ResponseEntity(response,HttpStatus.NOT_FOUND)
        }
        val result = ApiResponse(HttpStatus.OK.value(),"Profile $numberPlate Found",ProfileDto.fromProfileEntity(optionalProfile.get()))

        return ResponseEntity(result,HttpStatus.OK)
    }
}