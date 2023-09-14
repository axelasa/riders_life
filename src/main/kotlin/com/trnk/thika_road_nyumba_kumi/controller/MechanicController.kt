package com.trnk.thika_road_nyumba_kumi.controller

import com.trnk.thika_road_nyumba_kumi.dto.MechanicDto
import com.trnk.thika_road_nyumba_kumi.globalService.GlobalServices.Companion.mechanicService
import com.trnk.thika_road_nyumba_kumi.model.ApiResponse
import com.trnk.thika_road_nyumba_kumi.model.MechanicModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateMechanic
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
    @GetMapping("lat_lng")
    fun searchMechanicByLatLng(@RequestParam ( "latitude",required = true)latitude:Double,
                               @RequestParam ( "longitude",required = true)longitude:Double,
                               @RequestParam ( "distance",required = true)distance:Int,
                               @RequestParam (value = "page", defaultValue = "0", required = false)page:Int,
                              @RequestParam (value = "page_size", defaultValue = "10", required = false)pageSize:Int
                               ):ResponseEntity<Any>{

        val response = ApiResponse(HttpStatus.NOT_FOUND.value(),"No Mechanics Around You",null)
        val searchMechanic = mechanicService.searchMechanicLocationsWithinDistance(latitude,longitude,distance, pageRequest = PageRequest.of(page,pageSize) )
        if(searchMechanic.isEmpty()){
            return ResponseEntity(response,HttpStatus.NOT_FOUND)
        }
        val mechanicData = searchMechanic.map { MechanicDto.fromMechanicEntity(it) }.toList()
        val result =ApiResponse(HttpStatus.OK.value(),"Mechanic Found",mechanicData)
        return ResponseEntity(result,HttpStatus.OK)
    }
    @GetMapping("mechanics")
    fun getAllMechanics():List<MechanicDto> = mechanicService.getAllMechanics().stream().map {
        MechanicDto.fromMechanicEntity(it)
    }.toList()

}