package com.trnk.thika_road_nyumba_kumi.repos

import com.trnk.thika_road_nyumba_kumi.entities.MerchantEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MerchantRepo:JpaRepository<MerchantEntity,Long> {
    fun findByBusinessName(businessName:String): Optional<MerchantEntity>
    fun findMerchantByUsername(username:String):Optional<MerchantEntity>
}