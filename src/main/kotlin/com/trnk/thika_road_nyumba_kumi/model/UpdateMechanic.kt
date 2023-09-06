package com.trnk.thika_road_nyumba_kumi.model

import jakarta.validation.constraints.NotEmpty

 class UpdateMechanic(

    @field:NotEmpty(message = "bloodGroup is required") val firstname:String?,
    @field:NotEmpty(message = "Last Name is required")  val lastname:String?,
    @field:NotEmpty(message = "Last Name is required")  val username:String,
    @field:NotEmpty(message = "Phone Number is required") val phoneNumber:String?,
    @field:NotEmpty(message = "Location Name is required") val locationName:String?,
    @field:NotEmpty(message = "Latitude is required") val latitude:String?,
    @field:NotEmpty(message = "Longitude  is required") val longitude:String?,
)
