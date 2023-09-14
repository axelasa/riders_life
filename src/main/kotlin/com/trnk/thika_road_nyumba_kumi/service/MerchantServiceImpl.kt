package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.MerchantEntity
import com.trnk.thika_road_nyumba_kumi.exceptions.ControllerExceptionHandler
import com.trnk.thika_road_nyumba_kumi.model.MerchantModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateMerchant
import com.trnk.thika_road_nyumba_kumi.repos.MerchantRepo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class MerchantServiceImpl(private val merchantRepo: MerchantRepo) : MerchantService {
    override fun createNewMerchant(merchantModel: MerchantModel): MerchantEntity {
        val existingMerchant = getMerchantByBusinessName(merchantModel.businessName)
        if (existingMerchant.isPresent) throw ControllerExceptionHandler.conflicts(" Merchant Already Exists ")

        val newMerchant = MerchantEntity.createNewMerchant(merchantModel)
       return  merchantRepo.save(newMerchant)
    }

    override fun getMerchantByBusinessName(businessName: String): Optional<MerchantEntity> {
       return merchantRepo.findByBusinessName(businessName)
    }

    override fun getMerchantByUsername(username: String): Optional<MerchantEntity> {
        return merchantRepo.findMerchantByUsername(username)
    }
    override fun updateNewMerchant(updateMerchant: UpdateMerchant): MerchantEntity {
        val existingMerchant = getMerchantByUsername(updateMerchant.businessName)

        when{
            existingMerchant.isEmpty -> throw ControllerExceptionHandler.conflicts("This Merchant ${updateMerchant.businessName.uppercase()} Does Not Exist")
            else -> {
                val merchantUpdate = existingMerchant.get()

                if (updateMerchant.firstname != null) merchantUpdate.firstname = updateMerchant.firstname
                if (updateMerchant.lastname != null) merchantUpdate.lastname = updateMerchant.lastname
                if (updateMerchant.username != null) merchantUpdate.username = updateMerchant.username
                if (updateMerchant.phoneNumber != null) merchantUpdate.phoneNumber = updateMerchant.phoneNumber
                if (updateMerchant.link != null) merchantUpdate.link = updateMerchant.link
                 merchantUpdate.updatedAt = Date()
                return merchantRepo.save(merchantUpdate)
            }
        }
    }
    override fun getAllMerchants(): List<MerchantEntity> {
       return  merchantRepo.findAll()
    }
}