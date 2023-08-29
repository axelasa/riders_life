package com.trnk.thika_road_nyumba_kumi.model

import jakarta.validation.constraints.NotEmpty

data class UserModel (
    @field:NotEmpty(message = "firstname is required") val firstname:String,
    @field:NotEmpty(message = "lastname is required") val lastname:String,
    @field:NotEmpty(message = "idNumber is required") val idNumber:String,
    @field:NotEmpty(message = "password is required") val password:String,
)
