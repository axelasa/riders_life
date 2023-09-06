package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.MechanicEntity
import com.trnk.thika_road_nyumba_kumi.exceptions.ControllerExceptionHandler
import com.trnk.thika_road_nyumba_kumi.model.MechanicModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateMechanic
import com.trnk.thika_road_nyumba_kumi.repos.MechanicRepo
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class MechanicServiceImpl(private val mecRepo:MechanicRepo) : MechanicService {
    override fun createMechanic(mechanic: MechanicModel): MechanicEntity {
        val existingMec = getMechanicByName(mechanic.username)
        if (existingMec.isPresent) throw ControllerExceptionHandler.conflicts("This Mechanic Already Exists")

        val newMec = MechanicEntity.createNewMechanic(mechanic)
        return mecRepo.save(newMec)
    }

    override fun updateMechanic(mechanic: UpdateMechanic): MechanicEntity {
       val existingMec = getMechanicByName(mechanic.username)

       //if(existingMec.isEmpty) throw ControllerExceptionHandler.notFound("This Mechanic ${mechanic.username} Has Not Yet Been Registered")

        val updateMec = existingMec.get()
        when{
            existingMec.isEmpty -> throw  ControllerExceptionHandler.notFound("This Mechanic ${mechanic.username} Has Not Yet Been Registered")

            else ->{
                val updateMec = existingMec.get()
                if (mechanic.firstname!=null) updateMec.firstname = mechanic.firstname
                if (mechanic.lastname!=null) updateMec.lastname = mechanic.lastname
                if (mechanic.locationName!=null) updateMec.locationName = mechanic.locationName
                if (mechanic.latitude!=null) updateMec.latitude = mechanic.latitude
                if (mechanic.longitude!=null) updateMec.longitude = mechanic.longitude
                if (mechanic.phoneNumber!=null) updateMec.phoneNumber = mechanic.phoneNumber

                updateMec.updatedAt = Date()
                return mecRepo.save(updateMec)
            }
        }
    }

    override fun getMechanicByName(username: String): Optional<MechanicEntity> {
        return mecRepo.findByUsername(username)
    }

    override fun searchMechanicLocationsWithinDistance(targetLat: Double, targetLon: Double, distance:Int,pageRequest: PageRequest): List<MechanicEntity> {
        val limit:Int = pageRequest.pageSize
        val offset:Int = limit * pageRequest.pageNumber
        return mecRepo.findLocationsWithinDistance(targetLat, targetLon, distance, limit, offset )
    }

    override fun getAllMechanics(): List<MechanicEntity> {
       return  mecRepo.findAll()
    }
}