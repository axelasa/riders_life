package com.trnk.thika_road_nyumba_kumi.entities

import com.trnk.thika_road_nyumba_kumi.model.MerchantModel
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "merchants")
class MerchantEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long?,
    val createdAt: Date,
    var firstname:String,
    var lastname:String,
    var username:String,
    var phoneNumber:String,
    val businessName:String,
    val idNumber:String,
    var link:String,
    var updatedAt:Date
){
    companion object{

        val now:Date = Date()
        fun createNewMerchant(merchantModel: MerchantModel):MerchantEntity {
           val newMerchant = MerchantEntity(null,now,merchantModel.firstname,merchantModel.username,merchantModel.phoneNumber,merchantModel.lastname,merchantModel.businessName,merchantModel.idNumber,merchantModel.link, now)
            return newMerchant
        }

    }
}