package com.trnk.thika_road_nyumba_kumi.model

import jakarta.validation.constraints.NotEmpty

 class LoginModel (
    @field:NotEmpty(message = "username is required") val username:String,
    @field:NotEmpty(message = "password is required") val password:String
)