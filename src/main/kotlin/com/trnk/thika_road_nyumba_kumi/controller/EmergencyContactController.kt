package com.trnk.thika_road_nyumba_kumi.controller

import com.trnk.thika_road_nyumba_kumi.dto.EmergencyContactDto
import com.trnk.thika_road_nyumba_kumi.globalService.GlobalServices.Companion.emergencyContactService
import com.trnk.thika_road_nyumba_kumi.model.ApiResponse
import com.trnk.thika_road_nyumba_kumi.model.EmergencyContactModel
import com.trnk.thika_road_nyumba_kumi.model.EmergencyContactUpdate
import jakarta.validation.Valid
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
class EmergencyContactController {
@PostMapping("create_emergency_contact")
fun createEmergencyContact(@RequestBody emergencyContactModel:EmergencyContactModel): ResponseEntity<Any> {
    val newContact = emergencyContactService.createEmergencyContacts(emergencyContactModel)
    val result = ApiResponse(HttpStatus.OK.value(),"Contact ${newContact.firstname} ${newContact.lastname} has been created successfully",EmergencyContactDto.fromEmergencyContactEntity(newContact))
    return ResponseEntity(result,HttpStatus.OK)
}

 @PutMapping("update_emergency_contact")
 fun updateEmergencyContact(@RequestBody emergencyContactUpdate: EmergencyContactUpdate):ResponseEntity<Any>{
    val newContact = emergencyContactService.updateEmergencyContacts(emergencyContactUpdate)
     val result = ApiResponse(HttpStatus.OK.value(),"Contact ${newContact.firstname} ${newContact.lastname} has been created successfully",EmergencyContactDto.fromEmergencyContactEntity(newContact))
     return ResponseEntity(result,HttpStatus.OK)
 }
    @GetMapping("contact")
    fun getContacts(@Valid @RequestParam("relations", required = true)relations:String):ResponseEntity<Any>{
        val response = ApiResponse(HttpStatus.NOT_FOUND.value(),"contact person $relations Not Found",null)
        val optionalContactPerson = emergencyContactService.getEmergencyContactByRelations(relations)
        if(optionalContactPerson.isEmpty){
            return ResponseEntity(response,HttpStatus.NOT_FOUND)
        }
        val result = ApiResponse(HttpStatus.OK.value(),"contact person $relations Found",EmergencyContactDto.fromEmergencyContactEntity(optionalContactPerson.get()))
        return ResponseEntity(result,HttpStatus.OK)
    }
    @GetMapping("all_contact")
    fun getAllContacts():List<EmergencyContactDto> = emergencyContactService.getAllEmergencyContacts().stream().map {
        EmergencyContactDto.fromEmergencyContactEntity(it)
    }.toList()
}