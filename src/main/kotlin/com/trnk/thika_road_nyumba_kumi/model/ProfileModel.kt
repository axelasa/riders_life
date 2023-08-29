package com.trnk.thika_road_nyumba_kumi.model

import jakarta.persistence.Column
import jakarta.validation.constraints.NotEmpty
import java.util.*

data class ProfileModel(
    @field:NotEmpty(message = "bloodGroup is required") val bloodGroup: String,
    @field:NotEmpty(message = "insurance name is required") val insurance: String,
    @field:NotEmpty(message = "insurance  number is required") val insuranceId: String,
    @field:NotEmpty(message = "numberPlate is required") val numberPlate: String,
    val roadLocation:String,
    @field:NotEmpty(message = "phoneNumber is required")  val phoneNumber: String,
)
