package com.trnk.thika_road_nyumba_kumi.model

import javax.validation.constraints.NotEmpty

 class UserModel (
    @field:NotEmpty(message = "firstname is required") val firstname:String,
    @field:NotEmpty(message = "lastname is required") val lastname:String,
    @field:NotEmpty(message = "idNumber is required") val idNumber:String,
    @field:NotEmpty(message = "password is required") val userPassword:String,
)
