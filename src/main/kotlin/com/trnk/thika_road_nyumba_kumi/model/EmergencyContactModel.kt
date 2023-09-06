package com.trnk.thika_road_nyumba_kumi.model

import jakarta.validation.constraints.NotEmpty

 class EmergencyContactModel(
    @field:NotEmpty(message = "firstname is required") val firstname:String,
    @field:NotEmpty(message = "lastname is required") val lastname:String,
    @field:NotEmpty(message = "idNumber is required") val idNumber:String,
    @field:NotEmpty(message = "phoneNumber is required") val phoneNumber:String,
    @field:NotEmpty(message = "relations is required") val relations:String,
)
