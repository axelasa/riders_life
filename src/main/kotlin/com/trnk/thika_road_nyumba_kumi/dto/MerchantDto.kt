package com.trnk.thika_road_nyumba_kumi.dto

import com.trnk.thika_road_nyumba_kumi.entities.MerchantEntity
import java.util.*

class MerchantDto (
    val id:Long?,
    val createdAt: Date,
    var firstname:String,
    var lastname:String,
    var username:String,
    var phoneNumber:String,
    val businessName:String,
//    val idNumber:String,
    var link:String,
    var updatedAt: Date
){
    companion object{
        fun fromMerchantEntity(m:MerchantEntity):MerchantDto{
            return MerchantDto(m.id,m.createdAt,m.firstname,m.lastname,m.username,m.phoneNumber,m.businessName,m.link,m.updatedAt)
        }
    }
}