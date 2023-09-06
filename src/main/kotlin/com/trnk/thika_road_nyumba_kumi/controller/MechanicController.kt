package com.trnk.thika_road_nyumba_kumi.controller

import com.trnk.thika_road_nyumba_kumi.dto.MechanicDto
import com.trnk.thika_road_nyumba_kumi.globalService.GlobalServices.Companion.mechanicService
import com.trnk.thika_road_nyumba_kumi.model.ApiResponse
import com.trnk.thika_road_nyumba_kumi.model.MechanicModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateMechanic
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("mechanic")
class MechanicController {
    @PostMapping("new_mechanic")
    fun createNewMechanic(@Valid @RequestBody mechanic:MechanicModel):ResponseEntity<Any>{
        val newMechanic = mechanicService.createMechanic(mechanic)
        val result = ApiResponse(HttpStatus.OK.value(),"Mechanic Created Successfully",MechanicDto.fromMechanicEntity(newMechanic))
        return ResponseEntity(result,HttpStatus.OK)
    }
    @PutMapping("update")
    fun updateMechanic( @Valid @RequestBody mechanic:UpdateMechanic):ResponseEntity<Any>{
        val existingMechanic = mechanicService.updateMechanic(mechanic)
        val result = ApiResponse(HttpStatus.OK.value(),"Mechanic Updated Successfully",MechanicDto.fromMechanicEntity(existingMechanic))
        return ResponseEntity(result,HttpStatus.OK)
    }

    @GetMapping("name")
    fun getMechanicByUserName(@Valid @RequestParam("username", required = true)username:String):ResponseEntity<Any>{
        val existingMechanic = mechanicService.getMechanicByName(username)
        val response = ApiResponse(HttpStatus.NOT_FOUND.value(),"This Mechanic $existingMechanic Does Not Exist",null)
        if (existingMechanic.isEmpty){
            return ResponseEntity(response,HttpStatus.NOT_FOUND)
        }
        val result = ApiResponse(HttpStatus.OK.value(),"Mechanic $existingMechanic Found",MechanicDto.fromMechanicEntity(existingMechanic.get()))
        return ResponseEntity(result,HttpStatus.OK)
    }
    @GetMapping("mechanics")
    fun getAllMechanics():List<MechanicDto> = mechanicService.getAllMechanics().stream().map {
        MechanicDto.fromMechanicEntity(it)
    }.toList()

}