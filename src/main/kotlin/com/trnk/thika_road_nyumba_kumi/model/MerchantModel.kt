package com.trnk.thika_road_nyumba_kumi.model

import jakarta.validation.constraints.NotEmpty

data class MerchantModel(
    @field:NotEmpty(message = "Firstname Cannot be Empty") val firstname:String,
    @field:NotEmpty(message = "Lastname Cannot be Empty") val lastname:String,
    @field:NotEmpty(message = "username Cannot be Empty") val username:String,
    @field:NotEmpty(message = "username Cannot be Empty") val phoneNumber:String,
    @field:NotEmpty(message = "Business Name Cannot be Empty") val businessName:String,
    @field:NotEmpty(message = "Id Number Cannot be Empty") val idNumber:String,
    @field:NotEmpty(message = "Business Link Cannot be Empty") val link:String,
)
