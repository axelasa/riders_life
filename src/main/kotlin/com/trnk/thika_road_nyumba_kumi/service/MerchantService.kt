package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.MerchantEntity
import com.trnk.thika_road_nyumba_kumi.model.MerchantModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateMerchant
import java.util.Optional

interface MerchantService {
    fun createNewMerchant(merchantModel: MerchantModel):MerchantEntity
    fun getMerchantByBusinessName(businessName:String):Optional<MerchantEntity>
    fun getMerchantByUsername(username:String):Optional<MerchantEntity>
    fun updateNewMerchant(updateMerchant: UpdateMerchant):MerchantEntity
    fun getAllMerchants():List<MerchantEntity>
}