package com.trnk.thika_road_nyumba_kumi.controller

import com.trnk.thika_road_nyumba_kumi.dto.MerchantDto
import com.trnk.thika_road_nyumba_kumi.globalService.GlobalServices.Companion.merchantService
import com.trnk.thika_road_nyumba_kumi.model.ApiResponse
import com.trnk.thika_road_nyumba_kumi.model.MerchantModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateMerchant
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/merchant")
class MerchantsController {
    @PostMapping("new_merchant")
    fun createNewMerchant(@Valid @RequestBody merchantModel:MerchantModel):ResponseEntity<Any> {
        val newMerchant = merchantService.createNewMerchant(merchantModel)
        val result = ApiResponse(HttpStatus.OK.value(),"Merchant Successfully Created",MerchantDto.fromMerchantEntity(newMerchant))

        return ResponseEntity(result,HttpStatus.OK)
    }
    @PutMapping("update_merchant")
    fun updateMerchant(@Valid @RequestBody updateMerchant: UpdateMerchant):ResponseEntity<Any>{
        val existingMerchant = merchantService.updateNewMerchant(updateMerchant)
        val result = ApiResponse(HttpStatus.OK.value(),"Merchant Successfully Updated",MerchantDto.fromMerchantEntity(existingMerchant))

        return ResponseEntity(result,HttpStatus.OK)
    }
    @GetMapping("business_name")
    fun getMerchantByBusinessName(@RequestParam("businessName", required = true)businessName:String):ResponseEntity<Any>{
        val merchant = merchantService.getMerchantByBusinessName(businessName)

        if(merchant.isEmpty){
            val response = ApiResponse(HttpStatus.NOT_FOUND.value(),"This Merchant Does Not Exist",null)
            return ResponseEntity(response,HttpStatus.NOT_FOUND)
        }
        val result = ApiResponse(HttpStatus.OK.value(),"Merchant $businessName Found Successfully",MerchantDto.fromMerchantEntity(merchant.get()))
        return ResponseEntity(result,HttpStatus.OK)
    }
    @GetMapping("username")
    fun getMerchantByUsername(@RequestParam("username", required = true)username:String):ResponseEntity<Any>{
       val businessOwner = merchantService.getMerchantByUsername(username)
        if (businessOwner.isEmpty){
            val response = ApiResponse(HttpStatus.NOT_FOUND.value(),"This Merchant Does Not Exist",null)
            return ResponseEntity(response,HttpStatus.NOT_FOUND)
        }
        val result = ApiResponse(HttpStatus.OK.value(),"Merchant $username Found Successfully",MerchantDto.fromMerchantEntity(businessOwner.get()))
        return ResponseEntity(result,HttpStatus.OK)
    }

    @GetMapping("merchants")
    fun getAllMerchants():List<MerchantDto> = merchantService.getAllMerchants().stream().map {
        MerchantDto.fromMerchantEntity(it)
    }.toList()

}